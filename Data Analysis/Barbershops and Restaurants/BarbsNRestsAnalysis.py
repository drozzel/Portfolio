# -*- coding: utf-8 -*-
"""
Created on Thu Apr 23 22:33:00 2020

@author: danie
"""

import requests
import json
import numpy as np
import pandas as pd
names = []
cities = []
prices = []
ratings = []
ids = []

#Seattle
api_key= "50Dl_V1VWuz82j2WHUiC4LzuJ1PeiyF7WMw-Vrxz1z_AV1X8yy79d1MobjbCn5GdrGopZKcTcz5KwJY64htwpAxX5Myo8SZMrA7zgrEJkBSX69SFbvx10MhRRgSiXnYx"
client_ID = "22UrrcJZUMBRPFuGDxUz1Q"

Seattle1 = requests.get("https://api.yelp.com/v3/businesses/search?term=barber&location=Seattle&price=1,2,3,4&limit=50&sort_by=review_count", headers = {"Authorization" : "Bearer " + api_key})
seattle_rest1 = json.loads(Seattle1.content.decode("utf-8"))
Seattle2 =requests.get("https://api.yelp.com/v3/businesses/search?term=barber&location=Seattle&price=1,2,3,4&sort_by=review_count&limit=50&offset=50", headers = {"Authorization" : "Bearer " + api_key})
seattle_rest2 = json.loads(Seattle2.content.decode("utf-8"))



counter = 0
for restaurant in seattle_rest1['businesses']:
    
    
    name = restaurant['name']
    
 
    
    city = 'Seattle'

    
    price= len(seattle_rest1['businesses'][counter]['price'])

    idnum = restaurant['id']
    
    reviews = requests.get("https://api.yelp.com/v3/businesses/" + idnum + "/reviews", headers={"Authorization" : "Bearer " + api_key})
    reviewscontent = json.loads(reviews.content)
    for n in np.arange(3):
        ratings.append(reviewscontent['reviews'][n]['rating'])
        names.append(name)
        cities.append(city)
        prices.append(price)
        ids.append(idnum)

    counter = counter +1
    
counter = 0
for restaurant in seattle_rest2['businesses']:
    
    
    name = restaurant['name']
 
    
    city = 'Seattle'

    
    price= len(seattle_rest2['businesses'][counter]['price'])

    idnum = restaurant['id']
    
    
    reviews = requests.get("https://api.yelp.com/v3/businesses/" + idnum + "/reviews", headers={"Authorization" : "Bearer " + api_key})
    reviewscontent = json.loads(reviews.content)
    for n in np.arange(3):
        ratings.append(reviewscontent['reviews'][n]['rating'])
        names.append(name)
        cities.append(city)
        prices.append(price)    
        ids.append(idnum)

    counter = counter +1
#
#    
#Chicago
Chicago1 = requests.get("https://api.yelp.com/v3/businesses/search?term=barber&location=Chicago&price=1,2,3,4&limit=50&sort_by=review_count", headers = {"Authorization" : "Bearer " + api_key})
chicago_rest1 = json.loads(Chicago1.content.decode("utf-8"))
Chicago2 =requests.get("https://api.yelp.com/v3/businesses/search?term=barber&location=Chicago&price=1,2,3,4&sort_by=review_count&limit=50&offset=50", headers = {"Authorization" : "Bearer " + api_key})
chicago_rest2 = json.loads(Chicago2.content.decode("utf-8"))



counter = 0
for restaurant in chicago_rest1['businesses']:
    
    
    name = restaurant['name']
 
    
    city = 'Chicago'

    
    price= len(chicago_rest1['businesses'][counter]['price'])

    idnum = restaurant['id']
    
    reviews = requests.get("https://api.yelp.com/v3/businesses/" + idnum + "/reviews", headers={"Authorization" : "Bearer " + api_key})
    reviewscontent = json.loads(reviews.content)
    for n in np.arange(3):
        ratings.append(reviewscontent['reviews'][n]['rating'])
        names.append(name)
        cities.append(city)
        prices.append(price)  
        ids.append(idnum)

    counter = counter +1
    
counter = 0
for restaurant in chicago_rest2['businesses']:
    
    
    name = restaurant['name']
 
    
    city = 'Chicago'

    
    price= len(seattle_rest2['businesses'][counter]['price'])

    idnum = restaurant['id']
    
    reviews = requests.get("https://api.yelp.com/v3/businesses/" + idnum + "/reviews", headers={"Authorization" : "Bearer " + api_key})
    reviewscontent = json.loads(reviews.content)
    for n in np.arange(3):
        ratings.append(reviewscontent['reviews'][n]['rating'])
        names.append(name)
        cities.append(city)
        prices.append(price)   
        ids.append(idnum)

    counter = counter +1
    
#Houston
Houston1 = requests.get("https://api.yelp.com/v3/businesses/search?term=barber&location=houston&price=1,2,3,4&limit=50&sort_by=review_count", headers = {"Authorization" : "Bearer " + api_key})
houston_rest1 = json.loads(Houston1.content.decode("utf-8"))
Houston2 =requests.get("https://api.yelp.com/v3/businesses/search?term=barber&location=houston&price=1,2,3,4&sort_by=review_count&limit=50&offset=50", headers = {"Authorization" : "Bearer " + api_key})
houston_rest2 = json.loads(Houston2.content.decode("utf-8"))



counter = 0
for restaurant in houston_rest1['businesses']:
    
    
    name = restaurant['name']
 
    
    city = 'Houston'

    
    price= len(houston_rest1['businesses'][counter]['price'])

    idnum = restaurant['id']
    
    reviews = requests.get("https://api.yelp.com/v3/businesses/" + idnum + "/reviews", headers={"Authorization" : "Bearer " + api_key})
    reviewscontent = json.loads(reviews.content)
    for n in np.arange(3):
        ratings.append(reviewscontent['reviews'][n]['rating'])
        names.append(name)
        cities.append(city)
        prices.append(price)   
        ids.append(idnum)

    counter = counter +1
    
counter = 0
for restaurant in houston_rest2['businesses']:
    
    
    name = restaurant['name']
 
    
    city = 'Houston'

    
    price= len(seattle_rest2['businesses'][counter]['price'])

    idnum = restaurant['id']
    
    reviews = requests.get("https://api.yelp.com/v3/businesses/" + idnum + "/reviews", headers={"Authorization" : "Bearer " + api_key})
    reviewscontent = json.loads(reviews.content)
    for n in np.arange(3):
        ratings.append(reviewscontent['reviews'][n]['rating'])
        names.append(name)
        cities.append(city)
        prices.append(price)   
        ids.append(idnum)

    counter = counter +1

##
#
#Denver
Denver1 = requests.get("https://api.yelp.com/v3/businesses/search?term=barber&location=Denver&price=1,2,3,4&limit=50&sort_by=review_count", headers = {"Authorization" : "Bearer " + api_key})
denver_rest1 = json.loads(Denver1.content.decode("utf-8"))
Denver2 =requests.get("https://api.yelp.com/v3/businesses/search?term=barber&location=Denver&price=1,2,3,4&sort_by=review_count&limit=50&offset=50", headers = {"Authorization" : "Bearer " + api_key})
denver_rest2 = json.loads(Denver2.content.decode("utf-8"))



counter = 0
for restaurant in denver_rest1['businesses']:
    
    
    name = restaurant['name']
 
    
    city = 'Denver'

    
    price= len(denver_rest1['businesses'][counter]['price'])

    idnum = restaurant['id']
    
    reviews = requests.get("https://api.yelp.com/v3/businesses/" + idnum + "/reviews", headers={"Authorization" : "Bearer " + api_key})
    reviewscontent = json.loads(reviews.content)
    for n in np.arange(3):
        ratings.append(reviewscontent['reviews'][n]['rating'])
        names.append(name)
        cities.append(city)
        prices.append(price)      
        ids.append(idnum)

    counter = counter +1
    
counter = 0
for restaurant in denver_rest2['businesses']:
    
    
    name = restaurant['name']
 
    
    city = 'Denver'

    
    price= len(denver_rest2['businesses'][counter]['price'])

    idnum = restaurant['id']
    
    reviews = requests.get("https://api.yelp.com/v3/businesses/" + idnum + "/reviews", headers={"Authorization" : "Bearer " + api_key})
    reviewscontent = json.loads(reviews.content)
    for n in np.arange(3):
        ratings.append(reviewscontent['reviews'][n]['rating'])
        names.append(name)
        cities.append(city)
        prices.append(price)   
        ids.append(idnum)

    counter = counter +1    

#
#
#Boston
Boston1 = requests.get("https://api.yelp.com/v3/businesses/search?term=barber&location=Boston&price=1,2,3,4&limit=50&sort_by=review_count", headers = {"Authorization" : "Bearer " + api_key})
boston_rest1 = json.loads(Boston1.content.decode("utf-8"))
Boston2 =requests.get("https://api.yelp.com/v3/businesses/search?term=barber&location=Boston&price=1,2,3,4&sort_by=review_count&limit=50&offset=50", headers = {"Authorization" : "Bearer " + api_key})
boston_rest2 = json.loads(Boston2.content.decode("utf-8"))



counter = 0
for restaurant in boston_rest1['businesses']:
    
    
    name = restaurant['name']
 
    
    city = 'Boston'

    
    price= len(boston_rest1['businesses'][counter]['price'])

    idnum = restaurant['id']
    
    reviews = requests.get("https://api.yelp.com/v3/businesses/" + idnum + "/reviews", headers={"Authorization" : "Bearer " + api_key})
    reviewscontent = json.loads(reviews.content)
    for n in np.arange(3):
        ratings.append(reviewscontent['reviews'][n]['rating'])
        names.append(name)
        cities.append(city)
        prices.append(price) 
        ids.append(idnum)
        

    counter = counter +1
    
counter = 0
for restaurant in boston_rest2['businesses']:
    
    
    name = restaurant['name']
 
    
    city = 'Boston'

    
    price= len(boston_rest2['businesses'][counter]['price'])

    idnum = restaurant['id']
    
    reviews = requests.get("https://api.yelp.com/v3/businesses/" + idnum + "/reviews", headers={"Authorization" : "Bearer " + api_key})
    reviewscontent = json.loads(reviews.content)
    for n in np.arange(3):
        ratings.append(reviewscontent['reviews'][n]['rating'])
        names.append(name)
        cities.append(city)
        prices.append(price)   
        ids.append(idnum)

    counter = counter +1
   
df = pd.DataFrame()
df['Business'] = names
df['City'] = cities
df['Price'] = prices
df['Review'] = ratings
df['ID'] = ids

df.to_csv('barbershops2.csv')