import requests
import json

#ref https://works.ioa.tw/weather/api/doc/index.html#api-Weather_API-GetHttpsWorksIoaTwWeatherApiUrlJson
#resp = requests.get('https://works.ioa.tw/weather/api/all.json')
resp = requests.get('https://works.ioa.tw/weather/api/weathers/244.json')
if resp.status_code != 200:
    # This means something went wrong.
    raise ApiError('GET /tasks/ {}'.format(resp.status_code))


print (resp.json()['img'])
json_str = resp.json()
print ('img',resp.json()['img'])
print ('desc',resp.json()['desc'])
print ('temperature',resp.json()['temperature'])
print ('felt_air_temp',resp.json()['felt_air_temp'])
print ('humidity',resp.json()['humidity'])
print ('rainfall',resp.json()['rainfall'])
print ('sunrise',resp.json()['sunrise'])
