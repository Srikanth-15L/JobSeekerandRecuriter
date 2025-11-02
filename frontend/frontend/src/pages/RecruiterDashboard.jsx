import React, { useState, useEffect } from 'react';
import { jobService, applicationService } from '../services/api';
import useAuthStore from '../store/authStore';

const RecruiterDashboard = () => {
  const user = useAuthStore((state) => state.user);
  const [jobs, setJobs] = useState([]);
  const [applications, setApplications] = useState([]);
  const [loading, setLoading] = useState(true);
  const [showJobForm, setShowJobForm] = useState(false);
  const [jobForm, setJobForm] = useState({
    jobTitle: '',
    companyName: '',
    jobLocation: '',
    jobType: 'JOB',
    jobDescription: '',
    postedDate: new Date().toISOString().split('T')[0],
    deadLineDate: '',
  });

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      const [jobsRes, appsRes] = await Promise.all([
        jobService.getJobsByRecruiter(user.email),
        applicationService.getByRecruiter(user.email),
      ]);
      setJobs(jobsRes.data);
      setApplications(appsRes.data);
    } catch (error) {
      console.error('Error fetching data:', error);
    } finally {
      setLoading(false);
    }
  };

  const handleJobFormChange = (e) => {
    setJobForm({
      ...jobForm,
      [e.target.name]: e.target.value,
    });
  };

  const handleCreateJob = async (e) => {
    e.preventDefault();
    try {
      await jobService.createJob({
        ...jobForm,
        recruiterEmail: user.email,
      });
      alert('Job posted successfully!');
      setShowJobForm(false);
      setJobForm({
        jobTitle: '',
        companyName: '',
        jobLocation: '',
        jobType: 'JOB',
        jobDescription: '',
        postedDate: new Date().toISOString().split('T')[0],
        deadLineDate: '',
      });
      fetchData();
    } catch (error) {
      alert('Failed to post job');
    }
  };

  const handleUpdateApplicationStatus = async (applicationId, status) => {
    try {
      await applicationService.updateStatus({
        applicatiionId: applicationId,
        status,
      });
      alert('Application status updated!');
      fetchData();
    } catch (error) {
      alert('Failed to update status');
    }
  };

  if (loading) {
    return <div className="loading"><div className="spinner"></div></div>;
  }

  return (
    <div style={styles.container}>
      <div style={styles.header}>
        <h1>Recruiter Dashboard</h1>
        <p>Welcome, {user?.name}!</p>
      </div>

      <div className="grid grid-3" style={{ marginBottom: '30px' }}>
        <div className="card" style={styles.statCard}>
          <h3>Posted Jobs</h3>
          <p style={styles.statNumber}>{jobs.length}</p>
        </div>
        <div className="card" style={styles.statCard}>
          <h3>Total Applications</h3>
          <p style={styles.statNumber}>{applications.length}</p>
        </div>
        <div className="card" style={styles.statCard}>
          <h3>Pending Review</h3>
          <p style={styles.statNumber}>
            {applications.filter((a) => a.status === 'APPLIED').length}
          </p>
        </div>
      </div>

      <div className="card" style={{ marginBottom: '30px' }}>
        <div style={styles.sectionHeader}>
          <h2>My Job Posts</h2>
          <button
            className="btn btn-primary"
            onClick={() => setShowJobForm(!showJobForm)}
          >
            {showJobForm ? 'Cancel' : 'Post New Job'}
          </button>
        </div>

        {showJobForm && (
          <form onSubmit={handleCreateJob} style={{ marginTop: '20px' }}>
            <div className="grid grid-2">
              <div className="form-group">
                <label>Job Title</label>
                <input
                  type="text"
                  name="jobTitle"
                  className="form-control"
                  value={jobForm.jobTitle}
                  onChange={handleJobFormChange}
                  required
                />
              </div>
              <div className="form-group">
                <label>Company Name</label>
                <input
                  type="text"
                  name="companyName"
                  className="form-control"
                  value={jobForm.companyName}
                  onChange={handleJobFormChange}
                  required
                />
              </div>
              <div className="form-group">
                <label>Location</label>
                <input
                  type="text"
                  name="jobLocation"
                  className="form-control"
                  value={jobForm.jobLocation}
                  onChange={handleJobFormChange}
                  required
                />
              </div>
              <div className="form-group">
                <label>Job Type</label>
                <select
                  name="jobType"
                  className="form-control"
                  value={jobForm.jobType}
                  onChange={handleJobFormChange}
                  required
                >
                  <option value="JOB">Full Time Job</option>
                  <option value="INTERNSHIP">Internship</option>
                </select>
              </div>
              <div className="form-group">
                <label>Deadline Date</label>
                <input
                  type="date"
                  name="deadLineDate"
                  className="form-control"
                  value={jobForm.deadLineDate}
                  onChange={handleJobFormChange}
                  required
                />
              </div>
            </div>
            <div className="form-group">
              <label>Job Description</label>
              <textarea
                name="jobDescription"
                className="form-control"
                rows="4"
                value={jobForm.jobDescription}
                onChange={handleJobFormChange}
                required
              ></textarea>
            </div>
            <button type="submit" className="btn btn-primary">
              Post Job
            </button>
          </form>
        )}

        <div className="grid grid-2" style={{ marginTop: '20px' }}>
          {jobs.map((job) => (
            <div key={job.id} className="card" style={styles.jobCard}>
              <h3 style={styles.jobTitle}>{job.jobTitle}</h3>
              <p style={styles.companyName}>{job.companyName}</p>
              <p><strong>Location:</strong> {job.jobLocation}</p>
              <p><strong>Type:</strong> <span className="badge badge-primary">{job.jobType}</span></p>
              <p style={styles.jobDescription}>{job.jobDescription?.substring(0, 100)}...</p>
              <div style={styles.jobFooter}>
                <span>Posted: {new Date(job.postedDate).toLocaleDateString()}</span>
                <span>
                  Applications: {applications.filter((a) => a.jobId === job.id).length}
                </span>
              </div>
            </div>
          ))}
        </div>
      </div>

      <div className="card">
        <h2 style={{ marginBottom: '20px' }}>Applications Received</h2>
        {applications.length === 0 ? (
          <p>No applications yet</p>
        ) : (
          <div style={styles.applicationList}>
            {applications.map((app) => (
              <div key={app.id} style={styles.applicationItem}>
                <div>
                  <strong>Job ID: {app.jobId}</strong>
                  <p>Applicant: {app.employeeEmail}</p>
                  <p>
                    Status: <span className={`badge badge-${getStatusClass(app.status)}`}>
                      {app.status}
                    </span>
                  </p>
                </div>
                <div style={styles.applicationActions}>
                  <button
                    className="btn btn-success"
                    style={{ marginRight: '8px' }}
                    onClick={() => handleUpdateApplicationStatus(app.id, 'SHORTLISTED')}
                    disabled={app.status === 'SHORTLISTED'}
                  >
                    Shortlist
                  </button>
                  <button
                    className="btn btn-danger"
                    onClick={() => handleUpdateApplicationStatus(app.id, 'REJECTED')}
                    disabled={app.status === 'REJECTED'}
                  >
                    Reject
                  </button>
                </div>
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
};

const getStatusClass = (status) => {
  switch (status) {
    case 'SHORTLISTED':
      return 'success';
    case 'REJECTED':
      return 'danger';
    case 'APPLIED':
      return 'warning';
    default:
      return 'primary';
  }
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
    background: 'linear-gradient(135deg, #10b981 0%, #059669 100%)',
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
  jobCard: {
    transition: 'transform 0.3s ease',
  },
  jobTitle: {
    color: '#2563eb',
    marginBottom: '8px',
  },
  companyName: {
    fontSize: '18px',
    fontWeight: '600',
    color: '#1f2937',
    marginBottom: '12px',
  },
  jobDescription: {
    color: '#6b7280',
    marginBottom: '16px',
  },
  jobFooter: {
    display: 'flex',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginTop: '16px',
    paddingTop: '16px',
    borderTop: '1px solid #e5e7eb',
  },
  applicationList: {
    display: 'flex',
    flexDirection: 'column',
    gap: '12px',
  },
  applicationItem: {
    padding: '16px',
    border: '1px solid #e5e7eb',
    borderRadius: '8px',
    display: 'flex',
    justifyContent: 'space-between',
    alignItems: 'center',
  },
  applicationActions: {
    display: 'flex',
    gap: '8px',
  },
};

export default RecruiterDashboard;
