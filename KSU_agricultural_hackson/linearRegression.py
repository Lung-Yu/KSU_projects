from sklearn.linear_model import LinearRegression
import matplotlib.pyplot as plt
from sklearn import datasets
X,y = datasets.make_regression(n_samples=200,n_features=1,n_targets=1,noise=10)
# plt.scatter(X,y,linewidths=0.1)
# plt.show()


model = LinearRegression()
model.fit(X,y)
predict = model.predict(X[:200,:])

plt.plot(X,predict,c="red")
plt.scatter(X,y)
plt.show()



# print (len(predict))
# print (X[0])
# print (predict[0])