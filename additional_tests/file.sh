
# UPLOAD FILE

curl -X POST http://localhost:8080/api/files \
     -H "Content-Type: multipart/form-data" \
     -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6W3siYXV0aG9yaXR5IjoiUk9MRV9BRE1JTiJ9XSwic3ViIjoiYWRtaW4iLCJpYXQiOjE3MDI2Nzk2MjUsImV4cCI6MTcwMjc2NjAyNX0.1FL_UUxg0yuK3mEAEVNYt1CIHhM92YyFQgucCJNNNjI" \
     -F "file=@book_with_knowledge.txt" \
     -F "isPublic=true"



# DOWNLOAD FILE

curl -X GET http://localhost:8080/api/files/0EH2FMAKTGTS8 \
     -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6W3siYXV0aG9yaXR5IjoiUk9MRV9BRE1JTiJ9XSwic3ViIjoiYWRtaW4iLCJpYXQiOjE3MDI2Nzk2MjUsImV4cCI6MTcwMjc2NjAyNX0.1FL_UUxg0yuK3mEAEVNYt1CIHhM92YyFQgucCJNNNjI" \
     -o download.txt


