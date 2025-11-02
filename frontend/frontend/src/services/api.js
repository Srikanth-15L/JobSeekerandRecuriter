import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('token');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 401) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      window.location.href = '/login';
    }
    return Promise.reject(error);
  }
);

export const authService = {
  register: (data) => api.post('/auth/register', data),
  login: (data) => api.post('/auth/login', data),
};

export const dashboardService = {
  getJobStats: () => api.get('/dashboard/jobs'),
  getApplicationStats: () => api.get('/dashboard/applications'),
  getUserStats: () => api.get('/dashboard/users'),
};

export const jobService = {
  getAllJobs: () => api.get('/jobposts'),
  getJobsByRecruiter: (email) => api.get(`/jobposts/recruiters/${email}`),
  createJob: (data) => api.post('/jobposts', data),
  searchByCompany: (companyName) => api.get(`/jobposts/search/${companyName}`),
  searchByTitle: (title) => api.get(`/jobposts/search/${title}`),
  searchByLocation: (location) => api.get(`/jobposts/search/${location}`),
};

export const applicationService = {
  apply: (data) => api.post('/applications/apply', data),
  getByEmployee: (email) => api.get(`/applications/job/${email}`),
  getByRecruiter: (email) => api.get(`/applications/job/${email}`),
  getByJobId: (jobId) => api.get(`/applications/job${jobId}`),
  updateStatus: (data) => api.put('/applications/status', data),
};

export const employeeService = {
  getProfile: (email) => api.get(`/employees/${email}`),
  updateProfile: (data) => api.post('/employees/update', data),
};

export const recruiterService = {
  getProfile: (email) => api.get(`/recruiters/${email}`),
  saveProfile: (data) => api.post('/recruiters/save', data),
};

export const courseService = {
  getAllCourses: () => api.get('/courses'),
  addCourse: (data) => api.post('/courses/add', data),
  deactivateCourse: (id) => api.put(`/courses/deactivate/${id}`),
};

export const subscriptionService = {
  create: (data) => api.post('/subscriptions', data),
  getByUserEmail: (email) => api.get(`/subscriptions/user/email/${email}`),
  getByEmployeeId: (id) => api.get(`/subscriptions/employee/${id}`),
  getByRecruiterId: (id) => api.get(`/subscriptions/recruiter/${id}`),
  getAll: () => api.get('/subscriptions'),
  downloadInvoice: (id) => api.get(`/subscriptions/invoice/${id}`, { responseType: 'blob' }),
};

export const fileService = {
  uploadResume: (file) => {
    const formData = new FormData();
    formData.append('file', file);
    return api.post('/uploadFiles/resume', formData, {
      headers: { 'Content-Type': 'multipart/form-data' },
    });
  },
  uploadImage: (file) => {
    const formData = new FormData();
    formData.append('file', file);
    return api.post('/uploadFiles/image', formData, {
      headers: { 'Content-Type': 'multipart/form-data' },
    });
  },
};

export const emailService = {
  sendEmail: (data) => api.post('/notifications/email', data),
};

export default api;
