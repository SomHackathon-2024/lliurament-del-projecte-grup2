
# UPLOAD FILE

curl -X POST http://localhost:8080/api/files \
     -H "Content-Type: multipart/form-data" \
     -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6W3siaWQiOjEsIm5hbWUiOiJST0xFX1VTRVIiLCJhdXRob3JpdHkiOiJST0xFX1VTRVIifV0sInN1YiI6InVzZXIiLCJpYXQiOjE3MDgyMzYyNzcsImV4cCI6MTcwODMyMjY3N30.ORrlwdBFSi-_WymBjD7wNgtZ3B7gDt4NgIP_KoS66S8" \
     -F "file=@/Users/morlok/Projects/lliurament-del-projecte-grup2/additional_tests/book_with_knowledge.txt"



# DOWNLOAD FILE

curl -X GET http://localhost:8080/api/files/0EH2FMAKTGTS8 \
     -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6W3siYXV0aG9yaXR5IjoiUk9MRV9BRE1JTiJ9XSwic3ViIjoiYWRtaW4iLCJpYXQiOjE3MDI2Nzk2MjUsImV4cCI6MTcwMjc2NjAyNX0.1FL_UUxg0yuK3mEAEVNYt1CIHhM92YyFQgucCJNNNjI" \
     -o download.txt