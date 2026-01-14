import axios from "axios";

const API_BASE_URL = process.env.REACT_APP_API_BASE; // must be set in AWS / Docker

export const createOrder = (orderData) => {
    return axios.post(`${API_BASE_URL}/api/orders`, orderData);
};
