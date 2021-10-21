# -*- coding: utf-8 -*-
"""
Created on Thu Apr 23 22:37:26 2020

@author: danie
"""

import pandas as pd
import seaborn as sns
import scipy.stats as scipy
import matplotlib.pyplot as plt


df1 = pd.read_csv('restaurants2.csv')
df_rest = df1[['City','Review']]
facet = sns.FacetGrid(df_rest,col = 'City')
facet.map(plt.hist,'Review',range =(1,5))
facet.set_titles("{col_name}")
plt.subplots_adjust(top=.8)
facet.fig.suptitle('Restaurants and Ratings')
plt.show()



df2 = pd.read_csv('barbershops2.csv')
df2_rest = df2[['City','Review']]
facet = sns.FacetGrid(df2_rest,col = 'City')
facet.map(plt.hist,'Review',range =(1,5))
facet.set_titles("{col_name}")
plt.subplots_adjust(top=.8)
facet.fig.suptitle('Barbershops and Ratings')
plt.show()

stat_df = df2[['ID','Review','Price']]
ratingavg = round(stat_df.groupby('ID').Review.mean(),2)
stat_df["Rating_Average"] = ratingavg
price = stat_df[['ID','Price']].drop_duplicates()
price_rating=pd.merge(ratingavg,price,left_on ='ID',right_on = 'ID')
price_rating.plot.scatter(x='Review',y='Price')
plt.title("Rating vs Price (Barbershops)")
plt.show()
print(scipy.stats.pearsonr(price_rating['Review'], price_rating['Price']))

