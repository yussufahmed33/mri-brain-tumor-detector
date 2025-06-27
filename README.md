# 🧠 MRI Brain Tumor Detection System

A full-stack web application that allows users to upload **MRI brain scans** and get **AI-powered tumor predictions**. The system uses:

- 🖼️ Spring Boot (Frontend) for file upload and REST integration  
- 🚀 FastAPI (Backend) for processing and prediction  
- 🧠 Machine Learning model for classifying brain tumors  

## 🔍 Features

- Upload MRI scan images (JPG/PNG)
- Automatically detects tumor type:
  - **Glioma**
  - **Meningioma**
  - **Pituitary**
  - or **No Tumor**
- Clean UI with responsive design
- Simple REST-based communication between frontend and backend

---

## 🧱 Tech Stack

| Layer       | Technology         |
|-------------|--------------------|
| Frontend    | Spring Boot (Java) |
| Backend     | FastAPI (Python)   |
| ML Model    | Scikit-learn or similar |
| Communication | REST API + JSON |
| Deployment  | Docker (optional)  |

---

## 🚀 How It Works

1. **User uploads** an MRI image from the Spring Boot web form.
2. The image is sent to the **FastAPI backend** using `RestTemplate.exchange`.
3. The backend loads the trained ML model and **predicts the tumor class**.
4. The result (as a string) is sent back and **displayed to the user**.

---

## 🧪 Sample Prediction

| Input Image | Predicted Tumor Type |
|-------------|-----------------------|
| ![sample](docs/sample_mri.jpg) | Glioma |

---

## 🛠️ Getting Started

### 1. Clone the repo

```bash
git clone https://github.com/your-username/mri-brain-tumor-detector.git
cd mri-brain-tumor-detector
