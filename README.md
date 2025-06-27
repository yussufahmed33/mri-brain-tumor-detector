# 🧠 Brain Tumor Detection System Using MRI Scans

A full-stack web application that allows users to upload **brain MRI images** and receive **AI-based predictions** about the type of tumor. The system uses:

* 🖼️ A Java-based web interface that sends images to FastAPI via REST API  
* 🚀 FastAPI (backend) to process the image and return the prediction  
* 🧠 A machine learning model to classify brain tumors  

---

## 🔍 Features

* Upload brain MRI image (JPG/PNG)
* Automatically detects tumor type:
  * **Glioma Tumor**
  * **Meningioma Tumor**
  * **Pituitary Tumor**
  * or **No Tumor**
* Simple and fast user interface
* Lightweight and smooth REST communication between Java application and FastAPI server

---

## 🧱 Tech Stack

| Layer         | Technology              |
| ------------- | ----------------------- |
| Interface     | Java + HTML             |
| Backend       | FastAPI (Python)        |
| ML Model      | Scikit-learn or similar |
| Communication | REST API + JSON         |
| Deployment    | Docker (optional)       |

---

## 🚀 How the System Works

1. **User uploads** an MRI image through the interface.  
2. The Java application sends the image to **FastAPI** via REST API.  
3. The backend loads the ML model and predicts **the tumor type**.  
4. The result is shown to the user as a simple text message.  

---

## 🧪 Sample Prediction

| Input Image                    | Predicted Tumor Type |
| ------------------------------ | -------------------- |
| ![sample](docs/sample_mri.jpg) | Glioma               |

---

## 🛠️ Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/yussufahmed33/mri-brain-tumor-detector
cd mri-brain-tumor-detector
```

### 2. Run the FastAPI Backend

```bash
cd Python/
pip install -r requirements.txt
uvicorn main:app --reload --port 8000
```

### 3. Run the Java Spring

```bash
cd JavaSpring/
./mvnw spring-boot:run
```

Then visit: `http://localhost:8080`

### 4. Install Python Dependencies

If you're using a virtual environment (recommended), do the following inside the `Python/` directory:

```bash
# Create a virtual environment
python -m venv venv

# Activate the virtual environment
# On Windows:
venv\Scripts\activate
# On macOS/Linux:
source venv/bin/activate

# Then install dependencies
pip install -r requirements.txt
```

---

## 🧠 The AI Model

* Trained on labeled MRI dataset (e.g., Kaggle brain tumor dataset)  
* Preprocessing includes resizing, grayscale conversion, etc.  
* Classification using ML algorithms like RandomForest or SVM  
* Dataset folders should be placed inside the project like this:

```
mri-brain-tumor-detector/
└── dataset/
    ├── Training/
    └── Testing/
```

And used in code like this:

```python
import os

base_path = os.path.join(os.getcwd(), 'dataset')
train_path = os.path.join(base_path, 'Training')
test_path = os.path.join(base_path, 'Testing')
```

---

## 📁 Project Structure

```
mri-brain-tumor-detector/
├── Python/
│   ├── main.py
│   ├── model.pkl
│   ├── requirements.txt
│   └── utils/
├── JavaSpring/
│   ├── src/
│   ├── templates/
│   └── application.properties
├── dataset/
│   ├── Training/
│   └── Testing/
├── docs/
│   └── sample_mri.jpg
├── .gitignore
└── README.md
```

---

## 📄 License

MIT License – Free for personal and educational use.

---

## ✨ Credits

* Developed by [Yussuf Ahmed]  
* MRI Dataset: [Kaggle Brain MRI Dataset](https://www.kaggle.com/datasets/masoudnickparvar/brain-tumor-mri-dataset)
