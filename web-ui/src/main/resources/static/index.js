angular.module('app', []).controller('indexPageController', function ($scope, $http) {
    const contextPath = 'http://localhost:8790/cloud/product';
    $scope.page = 0;

    const headerDict = {
        'Content-Type': 'Access-Control-Allow-Origin',
        'Accept': 'application/json',
        'Access-Control-Allow-Headers': 'Content-Type',
    }
    const requestOptions = {
        headers: new Headers(headerDict),
    };

    $scope.filltable = function (page) {
        console.log("1");
        $http.get(contextPath + '/?page=' + page, requestOptions)
            .then(function (response) {
                $scope.ProductList = response.data.products;
                $scope.TotalPages = response.data.totalPages;
                $scope.CurrentPage = response.data.currentPage;
            });
    };

    $scope.filltable($scope.page);
});