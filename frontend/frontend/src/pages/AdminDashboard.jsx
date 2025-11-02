import React, { useState, useEffect } from 'react';
import { dashboardService, courseService } from '../services/api';
import useAuthStore from '../store/authStore';

const AdminDashboard = () => {
  const user = useAuthStore((state) => state.user);
  const [stats, setStats] = useState({
    jobs: null,
    applications: null,
    users: null,
  });
  const [courses, setCourses] = useState([]);
  const [loading, setLoading] = useState(true);
  const [showCourseForm, setShowCourseForm] = useState(false);
  const [courseForm, setCourseForm] = useState({
    courseTitle: '',
    courseDescription: '',
    courseCatogory: '',
    adminId: user?.id || '',
  });

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      const [jobStats, appStats, userStats, coursesRes] = await Promise.all([
        dashboardService.getJobStats(),
        dashboardService.getApplicationStats(),
        dashboardService.getUserStats(),
        courseService.getAllCourses(),
      ]);
      setStats({
        jobs: jobStats.data,
        applications: appStats.data,
        users: userStats.data,
      });
      setCourses(coursesRes.data);
    } catch (error) {
      console.error('Error fetching data:', error);
    } finally {
      setLoading(false);
    }
  };

  const handleCourseFormChange = (e) => {
    setCourseForm({
      ...courseForm,
      [e.target.name]: e.target.value,
    });
  };

  const handleAddCourse = async (e) => {
    e.preventDefault();
    try {
      await courseService.addCourse(courseForm);
      alert('Course added successfully!');
      setShowCourseForm(false);
      setCourseForm({
        courseTitle: '',
        courseDescription: '',
        courseCatogory: '',
        adminId: user?.id || '',
      });
      fetchData();
    } catch (error) {
      alert('Failed to add course');
    }
  };

  const handleDeactivateCourse = async (courseId) => {
    if (window.confirm('Are you sure you want to deactivate this course?')) {
      try {
        await courseService.deactivateCourse(courseId);
        alert('Course deactivated successfully!');
        fetchData();
      } catch (error) {
        alert('Failed to deactivate course');
      }
    }
  };

  if (loading) {
    return <div className="loading"><div className="spinner"></div></div>;
  }

  return (
    <div style={styles.container}>
      <div style={styles.header}>
        <h1>Admin Dashboard</h1>
        <p>Welcome, {user?.name}!</p>
      </div>

      <div style={{ marginBottom: '30px' }}>
        <h2 style={{ marginBottom: '16px' }}>Job Statistics</h2>
        <div className="grid grid-3">
          <div className="card" style={styles.statCard}>
            <h3>Total Jobs</h3>
            <p style={styles.statNumber}>{stats.jobs?.totalJobs || 0}</p>
          </div>
          <div className="card" style={styles.statCard}>
            <h3>Internships</h3>
            <p style={styles.statNumber}>{stats.jobs?.totalInternships || 0}</p>
          </div>
          <div className="card" style={styles.statCard}>
            <h3>Full Time</h3>
            <p style={styles.statNumber}>{stats.jobs?.totalFullTimeJobs || 0}</p>
          </div>
        </div>
      </div>

      <div style={{ marginBottom: '30px' }}>
        <h2 style={{ marginBottom: '16px' }}>Application Statistics</h2>
        <div className="grid grid-4">
          <div className="card" style={{ ...styles.statCard, background: 'linear-gradient(135deg, #3b82f6 0%, #2563eb 100%)' }}>
            <h3>Total Applications</h3>
            <p style={styles.statNumber}>{stats.applications?.totalApplications || 0}</p>
          </div>
          <div className="card" style={{ ...styles.statCard, background: 'linear-gradient(135deg, #10b981 0%, #059669 100%)' }}>
            <h3>Shortlisted</h3>
            <p style={styles.statNumber}>{stats.applications?.totalShortlisted || 0}</p>
          </div>
          <div className="card" style={{ ...styles.statCard, background: 'linear-gradient(135deg, #f59e0b 0%, #d97706 100%)' }}>
            <h3>Pending</h3>
            <p style={styles.statNumber}>{stats.applications?.totalPending || 0}</p>
          </div>
          <div className="card" style={{ ...styles.statCard, background: 'linear-gradient(135deg, #ef4444 0%, #dc2626 100%)' }}>
            <h3>Rejected</h3>
            <p style={styles.statNumber}>{stats.applications?.totalRejected || 0}</p>
          </div>
        </div>
      </div>

      <div style={{ marginBottom: '30px' }}>
        <h2 style={{ marginBottom: '16px' }}>User Statistics</h2>
        <div className="grid grid-4">
          <div className="card" style={styles.statCard}>
            <h3>Job Seekers</h3>
            <p style={styles.statNumber}>{stats.users?.totalJobseekers || 0}</p>
          </div>
          <div className="card" style={styles.statCard}>
            <h3>Recruiters</h3>
            <p style={styles.statNumber}>{stats.users?.totalRecruiters || 0}</p>
          </div>
          <div className="card" style={styles.statCard}>
            <h3>Paid Users</h3>
            <p style={styles.statNumber}>{stats.users?.totalPaidUsers || 0}</p>
          </div>
          <div className="card" style={styles.statCard}>
            <h3>Blocked Users</h3>
            <p style={styles.statNumber}>{stats.users?.totalBlockUsers || 0}</p>
          </div>
        </div>
      </div>

      <div className="card">
        <div style={styles.sectionHeader}>
          <h2>Course Management</h2>
          <button
            className="btn btn-primary"
            onClick={() => setShowCourseForm(!showCourseForm)}
          >
            {showCourseForm ? 'Cancel' : 'Add New Course'}
          </button>
        </div>

        {showCourseForm && (
          <form onSubmit={handleAddCourse} style={{ marginTop: '20px', marginBottom: '30px' }}>
            <div className="grid grid-2">
              <div className="form-group">
                <label>Course Title</label>
                <input
                  type="text"
                  name="courseTitle"
                  className="form-control"
                  value={courseForm.courseTitle}
                  onChange={handleCourseFormChange}
                  required
                />
              </div>
              <div className="form-group">
                <label>Category</label>
                <input
                  type="text"
                  name="courseCatogory"
                  className="form-control"
                  value={courseForm.courseCatogory}
                  onChange={handleCourseFormChange}
                  required
                />
              </div>
            </div>
            <div className="form-group">
              <label>Course Description</label>
              <textarea
                name="courseDescription"
                className="form-control"
                rows="4"
                value={courseForm.courseDescription}
                onChange={handleCourseFormChange}
                required
              ></textarea>
            </div>
            <button type="submit" className="btn btn-primary">
              Add Course
            </button>
          </form>
        )}

        <div className="grid grid-2" style={{ marginTop: '20px' }}>
          {courses.map((course) => (
            <div key={course.id} className="card" style={styles.courseCard}>
              <h3 style={styles.courseTitle}>{course.courseTitle}</h3>
              <p className="badge badge-primary">{course.courseCatogory}</p>
              <p style={styles.courseDescription}>{course.courseDescription}</p>
              <div style={styles.courseFooter}>
                <span className={course.active ? 'badge badge-success' : 'badge badge-danger'}>
                  {course.active ? 'Active' : 'Inactive'}
                </span>
                {course.active && (
                  <button
                    className="btn btn-danger"
                    onClick={() => handleDeactivateCourse(course.id)}
                  >
                    Deactivate
                  </button>
                )}
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

const styles = {
  container: {
    padding: '30px',
    maxWidth: '1400px',
    margin: '0 auto',
  },
  header: {
    marginBottom: '30px',
  },
  statCard: {
    textAlign: 'center',
    background: 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)',
    color: 'white',
  },
  statNumber: {
    fontSize: '36px',
    fontWeight: '700',
    margin: '10px 0',
  },
  sectionHeader: {
    display: 'flex',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginBottom: '20px',
  },
  courseCard: {
    transition: 'transform 0.3s ease',
  },
  courseTitle: {
    color: '#2563eb',
    marginBottom: '12px',
  },
  courseDescription: {
    color: '#6b7280',
    marginTop: '12px',
    marginBottom: '16px',
  },
  courseFooter: {
    display: 'flex',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginTop: '16px',
    paddingTop: '16px',
    borderTop: '1px solid #e5e7eb',
  },
};

export default AdminDashboard;
