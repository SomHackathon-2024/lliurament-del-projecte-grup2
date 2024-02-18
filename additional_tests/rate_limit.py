import time
import requests

url = "http://localhost:8080/api/activity"
headers = {"Authorization":"Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6W3siaWQiOjEsIm5hbWUiOiJST0xFX1VTRVIiLCJhdXRob3JpdHkiOiJST0xFX1VTRVIifV0sInN1YiI6InVzZXIiLCJpYXQiOjE3MDgyMzYyNzcsImV4cCI6MTcwODMyMjY3N30.ORrlwdBFSi-_WymBjD7wNgtZ3B7gDt4NgIP_KoS66S8"}

for _ in range(160):
    requests.get(url, headers=headers)
    
response = requests.get(url, headers=headers)
print(response.text)
assert response.status_code == 425

time.sleep(5)

response = requests.get(url, headers=headers)
print(response.status_code)
assert response.status_code == 425

time.sleep(5)

response = requests.get(url, headers=headers)
print(response.status_code)
assert response.status_code == 200

print("Success!")