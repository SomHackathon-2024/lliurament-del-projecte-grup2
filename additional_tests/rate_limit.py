import time
import requests

url = "http://localhost:8080/api/courses/1"

for _ in range(60):
    requests.get(url)
    
response = requests.get(url)
print(response.status_code)
assert response.status_code == 425

time.sleep(5)

response = requests.get(url)
print(response.status_code)
assert response.status_code == 425

time.sleep(5)

response = requests.get(url)
print(response.status_code)
assert response.status_code == 200

print("Success!")