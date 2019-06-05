implemented few APIs to interact with Imgur API for upload, view and delete image after successful authentication of user.

This application integrated with swagger for API testing and mapstruct for copying properties from one entity to another entity.

Configured server port with 8092 in application.properties file

Swagger URL - http://localhost:8092/swagger-ui.html#/

H2-Console - http://localhost:8092/h2-console

REST API

1. UserRegistration - /user/registration

2. Upload Image - /user/image (POST)
      Response:
        {
          "userImageId": 2,
          "userId": 1,
          "srcImageName": "sign.jpg",
          "imgurImageId": "3gRC71B",
          "imgurImageType": "image/jpeg",
          "imgurImageTitle": "null",
          "imgurImageDesc": "null",
          "imgurImageDeleteHash": "PkBnoiRLa4FA85R",
          "imgurImageLink": "https://i.imgur.com/3gRC71B.jpg",
          "uploadedDate": "2019-06-05T09:50:05.663+0000"
        }
3. View Image - /user/image/getImage (POST)
  Request:
      {
        "hash": "string",
        "password": "string",
        "userName": "string"
      }
  Note:Here, we need to set imgurImageId value of uploadImage api response.
4. Delete Image - /user/image (DELETE)
      Request:
      {
        "hash": "string",
        "password": "string",
        "userName": "string"
      }
      Note:Here, we need to set imgurImageDeleteHash value of uploadImage api response.
5. Get UserDetails - /user/{userName} 

