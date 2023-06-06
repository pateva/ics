# ics
The Image Classification Service (ICS) allows users to submit image URLs and get the linked image classified (tagged) based on their perceived content. To do this, the service relies on the image recognition service [Ximilar](https://docs.ximilar.com/).

The project was developed within the VMware Bulgaria Talent Boost 2023.

Table of content:
- [Overall architecture](https://github.com/pateva/ics/edit/main/README.md#overall-architectute)
- [Requests](https://github.com/pateva/ics/edit/main/README.md#requests)
- [Demo and snaps](https://github.com/pateva/ics/edit/main/README.md#demo-and-snaps)

#### Overall architecture

<img src="https://github.com/pateva/ics/assets/83903221/bb62792c-3082-4284-8387-9bcc4a8bd496" width=500/>

#### Requests:
- `GET` /images -> lists all analysed images from the DB

| Parameter  | Type | Required | Description |
| ------------- | ------------- | ------------- | ------------- |
| labels  | Array of strings  | No | Specifies the labels to filter the images. If provided, images with matching labels will be returned. |

Example response:
```json
[
    {
        "imageId": 9,
        "imageUrl": "https://example.com/image.jpg",
        "createdAt": "2023-05-12T23:25:26.762859",
        "updatedAt": "2023-05-12T23:25:26.762859",
        "width": 1485.0,
        "height": 835.0,
        "imageService": "Ximilar",
        "labels": [
            {
                "labelId": 67,
                "labelDescription": "example"
            },
            {
                "labelId": 65,
                "labelDescription": "label"
            }]
      },
      {
        "imageId": 10,
        "imageUrl": "https://example.com/image2.jpg",
        "createdAt": "2023-06-12T23:25:26.762859",
        "updatedAt": "2023-06-12T23:25:26.762859",
        "width": 1485.0,
        "height": 835.0,
        "imageService": "Ximilar",
        "labels": [
            {
                "labelId": 61,
                "labelDescription": "example2"
            },
            {
                "labelId": 65,
                "labelDescription": "label"
            }]
      }
]
```
- `POST` /images 

| Parameter  | Type | Required | Description |
| ------------- | ------------- | ------------- | ------------- |
| noCache  | boolean  | No | Defaults to false. When true, the service shall always perform categorisation, replacing any prior tags for the same image |

Example body:
```json
{
 "records": [
     {
         "_url": "https://example.com/image.jpg"
     }
 ]
}
```
Example response:
```json
 {
    "imageId": 10,
    "imageUrl": "https://example.com/image.jpg",
    "createdAt": "2023-05-13T12:25:58.22273",
    "updatedAt": "2023-05-13T12:25:58.22273",
    "width": 1338.0,
    "height": 896.0,
    "imageService": "Ximilar",
    "labels": [
        {
            "labelId": 68,
            "labelDescription": "easter"
        },
        {
            "labelId": 3,
            "labelDescription": "isolated"
        },
        {
            "labelId": 4,
            "labelDescription": "ladybug"
        }
      ]
   }
```
- `GET` /images/{id} -> returns an image with a specific image id
- `DELETE` /images/{id} -> deletes image with a specific imageID from the DB
---
- `GET` /labels - lists all labels from the DB
Example response:
```json
  [
    {
        "labelId": 1,
        "labelDescription": "illustration"
    },
    {
        "labelId": 2,
        "labelDescription": "vector"
    },
    {
        "labelId": 3,
        "labelDescription": "isolated"
    },
  ]
```
- `POST` /labels
Example body:
```json
{
    "name":"newLabel"
}
```
Example response:
```json
   {
    "labelId": 269,
    "labelDescription": "newLabel"
}
```

- `GET` /labels/{id} -> returns a label with a specific label id
- `DELETE` /labels/{id} -> deletes a label with a specific label id from the DB


#### Demo and snaps

<img src="https://github.com/pateva/ics/assets/83903221/0db7c54a-8eef-4136-8240-f8a23d4ae986" width=400/>
<img src="https://github.com/pateva/ics/assets/83903221/3a31bed4-07e3-440c-b982-606692b86d3f" width=400/>

<img src="https://github.com/pateva/ics/assets/83903221/6c05f5c2-0611-4418-bb84-60fbb4e9ff79" width=400/>
<img src="https://github.com/pateva/ics/assets/83903221/8d758ddf-1550-42bd-9bae-c94743f5f08f" width=400/>

<img src="https://github.com/pateva/ics/assets/83903221/4f34b33f-eca9-416e-a34e-00090dcbe5f6" width=400/>
<img src="https://github.com/pateva/ics/assets/83903221/5429fcc7-1891-44b2-8171-87c094474d6b" width=400/>

<img src="https://github.com/pateva/ics/assets/83903221/3a3f5171-ed34-4649-bf82-8b0ffa653e0c" width=400/>
<img src="https://github.com/pateva/ics/assets/83903221/d7becbb2-0db7-4cf7-941c-1848b5a2b4f6" width=400/>

Video: 

https://github.com/pateva/ics/assets/83903221/3bd38823-8d90-4e3d-a4c4-2c398c3e8218



