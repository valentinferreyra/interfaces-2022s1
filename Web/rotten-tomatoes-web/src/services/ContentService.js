import axios from "axios";

axios.defaults.baseURL = "http://localhost:7070";
axios.defaults.headers.post["Content-Type"] = "application/json";

const ContentService = {
  getAllCategories: () => {
    return axios.get("/categories");
  },

  getLatestContent: () => {
    return axios.get("/content/latest");
  },

  getContentTop: () => {
    return axios.get("/content/top");
  },

  getCategoryById: (id) => {
    return axios.get(`/categories/${id}`);
  },

  getContentById: (id) => {
    return axios.get(`/content/${id}`);
  },

  getUserById: (id) => {
    return axios.get(`/user/${id}`);
  },

  addReview: (contentId, text, stars) => {
    const token = window.localStorage.getItem("token");
    return axios.post(
      `/content/${contentId}`,
      {
        text,
        stars,
      },
      { headers: { Authorization: token } }
    );
  },
};

export default ContentService;
