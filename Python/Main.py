from fastapi import FastAPI, UploadFile, File

app = FastAPI()

#Intialize variables
import os
import cv2 as cv
import numpy as np
#import pandas as pd
import matplotlib.pyplot as plt
from sklearn.ensemble import RandomForestClassifier
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score, confusion_matrix, classification_report
base_path = os.path.join(os.getcwd(), 'dataset')
train_path = os.path.join(base_path, 'Training')
test_path = os.path.join(base_path, 'Testing')
categories = ['glioma', 'meningioma', 'notumor', 'pituitary']

X = []
y = []
results=[]

#Read training data
for label, category in enumerate(categories):
    folder = os.path.join(train_path, category)
    for file in os.listdir(folder):
        #processing training images
        img_path = os.path.join(folder, file)
        img = cv.imread(img_path)
        img = cv.resize(img, (64, 64))
        gray = cv.cvtColor(img, cv.COLOR_BGR2GRAY)
        X.append(gray.flatten())
        y.append(label)
#converting training images and categories to numpy array for better training
X = np.array(X)
y = np.array(y)
#training algorithm for the model
model = RandomForestClassifier()
model.fit(X, y)
print("training finished")
##########################################################################
########################################
########################################################################

@app.post("/get_predict")
async def get_predict(file: UploadFile = File(...)):
    contents = await file.read()
    np_array = np.frombuffer(contents, np.uint8)
    img = cv.imdecode(np_array, cv.IMREAD_COLOR)

    #processing training data
    #img = cv.imread(UploadFile)
    img = cv.resize(img, (64, 64))
    gray = cv.cvtColor(img, cv.COLOR_BGR2GRAY)
    flat_img = gray.flatten().reshape(1, -1)
    prediction = model.predict(flat_img)[0]
    predicted_label = categories[prediction]
    #presenting data
    #plt.imshow(cv.cvtColor(img, cv.COLOR_BGR2RGB))
    #plt.title(f'Prediction: {predicted_label}')
    #plt.axis('off')
    #plt.show()
    return predicted_label

