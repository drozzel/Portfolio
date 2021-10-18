# -*- coding: utf-8 -*-
"""
Created on Tue Mar 31 13:36:47 2020

@author: danie
"""
#Import Libraries
import pandas as pd
import matplotlib.pyplot as plt
import datetime as dt

#Organize Data
corona_cases = pd.read_csv('corona_cases.csv')
summaries = pd.read_csv('summaries.csv')

us = summaries.loc[summaries['Country_Region'] == 'US']

us = us[['Country_Region','Province_State','Last_Update','Confirmed','Deaths','Recovered','Active']]

country_deaths = summaries.groupby("Country_Region")["Deaths"].sum()
country_deaths.sort_values(ascending=False,inplace =True)

country_confirmed = summaries.groupby("Country_Region")["Confirmed"].sum()
country_confirmed.sort_values(ascending = False, inplace = True)

country_recovered = summaries.groupby("Country_Region")["Recovered"].sum()
country_recovered.sort_values(ascending = False,inplace = True)

top10Confirmed = pd.DataFrame(country_confirmed.iloc[:11]).reset_index()

top10Deaths =pd.DataFrame( country_deaths.iloc[:11]).reset_index()

Recovered = pd.DataFrame(summaries.groupby("Country_Region")['Recovered'].sum()).reset_index()
top10Countries = pd.merge(top10Confirmed,top10Deaths)
top10Countries = pd.merge(top10Countries,Recovered)
top10Countries.set_index('Country_Region',inplace = True)

#Top 10 Plots
top10Countries.Confirmed.plot(kind = 'barh', by = 'Country_Region',color = 'darkslategrey')
plt.title("Top 10 Countries by Confirmed Cases")
plt.ylabel("Country")
plt.xlabel("Number of Confirmed Cases")
plt.show()

top10Countries.Recovered.plot(kind='barh',by = 'Country_Region',color = 'lightcoral')
plt.title("Top 10 Countries and Amount of Recovered Cases")
plt.ylabel("Country")
plt.xlabel("Number of Recovered Cases")
plt.show()

top10Countries.Deaths.plot(kind = 'barh', by = 'Country_Region',color = 'purple')
plt.title("Top 10 Countries and Amount of Deaths")
plt.ylabel("Country")
plt.xlabel("Number of Deaths")
plt.show()


con_recovered = round(top10Countries['Recovered']/top10Countries['Confirmed']*100,2)
top10Countries['%Recovered'] =con_recovered
con_deaths = round(top10Countries['Deaths']/top10Countries['Confirmed']*100,2)
top10Countries['%Deaths']= con_deaths
top10Countries['%Deaths'].plot(kind = 'bar', by ='Country_Region', color = "mediumpurple")
plt.title('Top 10 Countries Percent of Death over Confirmed Cases')
plt.xlabel('Country')
plt.ylabel('% out of 100')
plt.show()

top10Countries['%Recovered'].plot(kind = 'bar', by ='Country_Region', color = "darkgreen")
plt.title('Top 10 Countries Percent of Recovered over Confirmed Cases')
plt.xlabel('Country')
plt.ylabel('% out of 100')
plt.show()

corona_us = corona_cases.loc[corona_cases['Country']=='US']


countries_pop = [17.18,66.44,8.57,51.47,66.99,81.16,82.79,46.66,327.2,60.48,1386]
countries_pop.reverse()
plt.show()

for i in range(len(countries_pop)):
    countries_pop[i] = (countries_pop[i]*1000000)
    
top10Countries['Population'] = countries_pop
con_pop = round(top10Countries['Confirmed']/top10Countries['Population']*100,2)
top10Countries['%ofPop'] = con_pop

top10Countries['%ofPop'].plot(kind='bar')
plt.title("Percentage of total Population Infected")
plt.ylabel("% out of 100")
plt.xlabel("Country")
plt.show()

top10Countries.to_csv(r'D:\DATA219\top10Countries.csv')



