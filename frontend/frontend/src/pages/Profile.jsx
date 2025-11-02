import React, { useState, useEffect } from 'react';
import { employeeService, recruiterService, fileService } from '../services/api';
import useAuthStore from '../store/authStore';

const Profile = () => {
  const { user } = useAuthStore();
  const isRecruiter = user?.role === 'ROLE_RECRUITER';
  const isEmployee = user?.role === 'ROLE_EMPLOYEE';

  const [profile, setProfile] = useState(null);
  const [loading, setLoading] = useState(true);
  const [editing, setEditing] = useState(false);
  const [formData, setFormData] = useState({});
  const [uploading, setUploading] = useState(false);

  useEffect(() => {
    fetchProfile();
  }, []);

  const fetchProfile = async () => {
    try {
      if (isEmployee) {
        const res = await employeeService.getProfile(user.email);
        setProfile(res.data);
        setFormData(res.data || {});
      } else if (isRecruiter) {
        const res = await recruiterService.getProfile(user.email);
        setProfile(res.data);
        setFormData(res.data || {});
      }
    } catch (error) {
      console.error('Error fetching profile:', error);
    } finally {
      setLoading(false);
    }
  };

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleFileUpload = async (e) => {
    const file = e.target.files[0];
    if (!file) return;

    setUploading(true);
    try {
      const res = await fileService.uploadResume(file);
      setFormData({
        ...formData,
        resumeURL: res.data,
      });
      alert('Resume uploaded successfully!');
    } catch (error) {
      alert('Failed to upload resume');
    } finally {
      setUploading(false);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (isEmployee) {
        await employeeService.updateProfile({ ...formData, email: user.email });
      } else if (isRecruiter) {
        await recruiterService.saveProfile({ ...formData, recruiterEmail: user.email });
      }
      alert('Profile updated successfully!');
      setEditing(false);
      fetchProfile();
    } catch (error) {
      alert('Failed to update profile');
    }
  };

  if (loading) {
    return <div className="loading"><div className="spinner"></div></div>;
  }

  return (
    <div style={styles.container}>
      <div className="card" style={styles.profileCard}>
        <div style={styles.header}>
          <h1>My Profile</h1>
          <button
            className="btn btn-primary"
            onClick={() => setEditing(!editing)}
          >
            {editing ? 'Cancel' : 'Edit Profile'}
          </button>
        </div>

        {editing ? (
          <form onSubmit={handleSubmit}>
            {isEmployee && (
              <>
                <div className="form-group">
                  <label>Full Name</label>
                  <input
                    type="text"
                    name="name"
                    className="form-control"
                    value={formData.name || ''}
                    onChange={handleChange}
                    required
                  />
                </div>
                <div className="form-group">
                  <label>Phone</label>
                  <input
                    type="tel"
                    name="phone"
                    className="form-control"
                    value={formData.phone || ''}
                    onChange={handleChange}
                  />
                </div>
                <div className="form-group">
                  <label>Qualification</label>
                  <input
                    type="text"
                    name="qualification"
                    className="form-control"
                    value={formData.qualification || ''}
                    onChange={handleChange}
                  />
                </div>
                <div className="form-group">
                  <label>Resume</label>
                  <input
                    type="file"
                    className="form-control"
                    onChange={handleFileUpload}
                    accept=".pdf,.doc,.docx"
                    disabled={uploading}
                  />
                  {uploading && <p>Uploading...</p>}
                  {formData.resumeURL && (
                    <a href={formData.resumeURL} target="_blank" rel="noopener noreferrer">
                      View Current Resume
                    </a>
                  )}
                </div>
              </>
            )}

            {isRecruiter && (
              <>
                <div className="form-group">
                  <label>Recruiter Name</label>
                  <input
                    type="text"
                    name="recruiterName"
                    className="form-control"
                    value={formData.recruiterName || ''}
                    onChange={handleChange}
                    required
                  />
                </div>
                <div className="form-group">
                  <label>Company Name</label>
                  <input
                    type="text"
                    name="companyName"
                    className="form-control"
                    value={formData.companyName || ''}
                    onChange={handleChange}
                  />
                </div>
                <div className="form-group">
                  <label>Phone</label>
                  <input
                    type="tel"
                    name="recruiterPhone"
                    className="form-control"
                    value={formData.recruiterPhone || ''}
                    onChange={handleChange}
                  />
                </div>
                <div className="form-group">
                  <label>Designation</label>
                  <input
                    type="text"
                    name="designation"
                    className="form-control"
                    value={formData.designation || ''}
                    onChange={handleChange}
                  />
                </div>
              </>
            )}

            <button type="submit" className="btn btn-primary">
              Save Changes
            </button>
          </form>
        ) : (
          <div style={styles.profileInfo}>
            {isEmployee && (
              <>
                <div style={styles.infoItem}>
                  <strong>Name:</strong> {profile?.name || 'Not set'}
                </div>
                <div style={styles.infoItem}>
                  <strong>Email:</strong> {user?.email}
                </div>
                <div style={styles.infoItem}>
                  <strong>Phone:</strong> {profile?.phone || 'Not set'}
                </div>
                <div style={styles.infoItem}>
                  <strong>Qualification:</strong> {profile?.qualification || 'Not set'}
                </div>
                <div style={styles.infoItem}>
                  <strong>Resume:</strong>{' '}
                  {profile?.resumeURL ? (
                    <a href={profile.resumeURL} target="_blank" rel="noopener noreferrer">
                      View Resume
                    </a>
                  ) : (
                    'Not uploaded'
                  )}
                </div>
              </>
            )}

            {isRecruiter && (
              <>
                <div style={styles.infoItem}>
                  <strong>Name:</strong> {profile?.recruiterName || 'Not set'}
                </div>
                <div style={styles.infoItem}>
                  <strong>Email:</strong> {user?.email}
                </div>
                <div style={styles.infoItem}>
                  <strong>Company:</strong> {profile?.companyName || 'Not set'}
                </div>
                <div style={styles.infoItem}>
                  <strong>Phone:</strong> {profile?.recruiterPhone || 'Not set'}
                </div>
                <div style={styles.infoItem}>
                  <strong>Designation:</strong> {profile?.designation || 'Not set'}
                </div>
              </>
            )}
          </div>
        )}
      </div>
    </div>
  );
};

const styles = {
  container: {
    padding: '30px',
    maxWidth: '800px',
    margin: '0 auto',
  },
  profileCard: {
    padding: '40px',
  },
  header: {
    display: 'flex',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginBottom: '30px',
  },
  profileInfo: {
    display: 'flex',
    flexDirection: 'column',
    gap: '20px',
  },
  infoItem: {
    fontSize: '16px',
    padding: '12px',
    backgroundColor: '#f9fafb',
    borderRadius: '6px',
  },
};

export default Profile;
