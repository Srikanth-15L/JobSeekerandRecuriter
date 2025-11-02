import React, { useState, useEffect } from 'react';
import { jobService, applicationService } from '../services/api';
import useAuthStore from '../store/authStore';

const EmployeeDashboard = () => {
  const user = useAuthStore((state) => state.user);
  const [jobs, setJobs] = useState([]);
  const [applications, setApplications] = useState([]);
  const [loading, setLoading] = useState(true);
  const [searchTerm, setSearchTerm] = useState('');

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      const [jobsRes, appsRes] = await Promise.all([
        jobService.getAllJobs(),
        applicationService.getByEmployee(user.email),
      ]);
      setJobs(jobsRes.data);
      setApplications(appsRes.data);
    } catch (error) {
      console.error('Error fetching data:', error);
    } finally {
      setLoading(false);
    }
  };

  const handleApply = async (jobId) => {
    try {
      await applicationService.apply({
        jobId,
        employeeEmail: user.email,
        resumeURL: '',
      });
      alert('Application submitted successfully!');
      fetchData();
    } catch (error) {
      alert('Failed to submit application');
    }
  };

  const filteredJobs = jobs.filter(
    (job) =>
      job.jobTitle?.toLowerCase().includes(searchTerm.toLowerCase()) ||
      job.companyName?.toLowerCase().includes(searchTerm.toLowerCase()) ||
      job.jobLocation?.toLowerCase().includes(searchTerm.toLowerCase())
  );

  if (loading) {
    return <div className="loading"><div className="spinner"></div></div>;
  }

  return (
    <div style={styles.container}>
      <div style={styles.header}>
        <h1>Employee Dashboard</h1>
        <p>Welcome, {user?.name}!</p>
      </div>

      <div className="grid grid-3" style={{ marginBottom: '30px' }}>
        <div className="card" style={styles.statCard}>
          <h3>Available Jobs</h3>
          <p style={styles.statNumber}>{jobs.length}</p>
        </div>
        <div className="card" style={styles.statCard}>
          <h3>My Applications</h3>
          <p style={styles.statNumber}>{applications.length}</p>
        </div>
        <div className="card" style={styles.statCard}>
          <h3>Shortlisted</h3>
          <p style={styles.statNumber}>
            {applications.filter((a) => a.status === 'SHORTLISTED').length}
          </p>
        </div>
      </div>

      <div className="card">
        <h2 style={{ marginBottom: '20px' }}>My Applications</h2>
        {applications.length === 0 ? (
          <p>No applications yet</p>
        ) : (
          <div style={styles.applicationList}>
            {applications.map((app) => (
              <div key={app.id} style={styles.applicationItem}>
                <div>
                  <strong>Job ID: {app.jobId}</strong>
                  <p>Status: <span className={`badge badge-${getStatusClass(app.status)}`}>
                    {app.status}
                  </span></p>
                </div>
              </div>
            ))}
          </div>
        )}
      </div>

      <div className="card" style={{ marginTop: '30px' }}>
        <div style={styles.searchHeader}>
          <h2>Available Jobs</h2>
          <input
            type="text"
            placeholder="Search jobs..."
            className="form-control"
            style={{ maxWidth: '300px' }}
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
          />
        </div>

        <div className="grid grid-2" style={{ marginTop: '20px' }}>
          {filteredJobs.map((job) => (
            <div key={job.id} className="card" style={styles.jobCard}>
              <h3 style={styles.jobTitle}>{job.jobTitle}</h3>
              <p style={styles.companyName}>{job.companyName}</p>
              <p><strong>Location:</strong> {job.jobLocation}</p>
              <p><strong>Type:</strong> <span className="badge badge-primary">{job.jobType}</span></p>
              <p style={styles.jobDescription}>{job.jobDescription?.substring(0, 100)}...</p>
              <div style={styles.jobFooter}>
                <span>Posted: {new Date(job.postedDate).toLocaleDateString()}</span>
                <button
                  className="btn btn-primary"
                  onClick={() => handleApply(job.id)}
                  disabled={applications.some((a) => a.jobId === job.id)}
                >
                  {applications.some((a) => a.jobId === job.id) ? 'Applied' : 'Apply Now'}
                </button>
              </div>
            </div>
          ))}
        </div>
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
    background: 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)',
    color: 'white',
  },
  statNumber: {
    fontSize: '36px',
    fontWeight: '700',
    margin: '10px 0',
  },
  searchHeader: {
    display: 'flex',
    justifyContent: 'space-between',
    alignItems: 'center',
    flexWrap: 'wrap',
    gap: '20px',
  },
  jobCard: {
    transition: 'transform 0.3s ease',
    cursor: 'pointer',
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
};

export default EmployeeDashboard;
