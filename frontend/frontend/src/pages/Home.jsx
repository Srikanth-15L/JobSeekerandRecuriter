import React from 'react';
import { Link } from 'react-router-dom';
import useAuthStore from '../store/authStore';

const Home = () => {
  const { isAuthenticated, user } = useAuthStore();

  const getDashboardLink = () => {
    if (!isAuthenticated) return '/login';

    switch (user?.role) {
      case 'ADMIN':
        return '/admin/dashboard';
      case 'ROLE_RECRUITER':
        return '/recruiter/dashboard';
      case 'ROLE_EMPLOYEE':
        return '/employee/dashboard';
      default:
        return '/login';
    }
  };

  return (
    <div style={styles.container}>
      <div style={styles.hero}>
        <h1 style={styles.title}>Find Your Dream Job</h1>
        <p style={styles.subtitle}>
          Connect with top employers and explore thousands of opportunities
        </p>
        <div style={styles.buttons}>
          {isAuthenticated ? (
            <Link to={getDashboardLink()} className="btn btn-primary" style={styles.btn}>
              Go to Dashboard
            </Link>
          ) : (
            <>
              <Link to="/register" className="btn btn-primary" style={styles.btn}>
                Get Started
              </Link>
              <Link to="/login" className="btn btn-outline" style={styles.btn}>
                Sign In
              </Link>
            </>
          )}
        </div>
      </div>

      <div style={styles.features}>
        <div className="container">
          <h2 style={styles.featuresTitle}>Why Choose Us</h2>
          <div className="grid grid-3">
            <div className="card" style={styles.featureCard}>
              <div style={styles.icon}>👔</div>
              <h3>For Job Seekers</h3>
              <p>Browse thousands of jobs and apply with ease. Track your applications in real-time.</p>
            </div>
            <div className="card" style={styles.featureCard}>
              <div style={styles.icon}>🏢</div>
              <h3>For Recruiters</h3>
              <p>Post jobs, review applications, and find the perfect candidates for your company.</p>
            </div>
            <div className="card" style={styles.featureCard}>
              <div style={styles.icon}>📚</div>
              <h3>Learning Courses</h3>
              <p>Access professional courses to enhance your skills and advance your career.</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

const styles = {
  container: {
    minHeight: '100vh',
  },
  hero: {
    background: 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)',
    color: 'white',
    padding: '100px 20px',
    textAlign: 'center',
  },
  title: {
    fontSize: '48px',
    fontWeight: '700',
    marginBottom: '20px',
  },
  subtitle: {
    fontSize: '20px',
    marginBottom: '40px',
    maxWidth: '600px',
    margin: '0 auto 40px',
  },
  buttons: {
    display: 'flex',
    gap: '16px',
    justifyContent: 'center',
    flexWrap: 'wrap',
  },
  btn: {
    fontSize: '18px',
    padding: '12px 32px',
  },
  features: {
    padding: '80px 20px',
    backgroundColor: 'white',
  },
  featuresTitle: {
    fontSize: '36px',
    fontWeight: '700',
    textAlign: 'center',
    marginBottom: '60px',
  },
  featureCard: {
    textAlign: 'center',
    padding: '40px 24px',
  },
  icon: {
    fontSize: '48px',
    marginBottom: '20px',
  },
};

export default Home;
