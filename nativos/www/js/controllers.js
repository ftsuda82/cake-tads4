angular.module('starter.controllers', ['ionic', 'ngCordova'])

        // CONFIGURACAO PARA LIMPAR A URL DA IMAGEM
        .config(function ($compileProvider) {
          $compileProvider.imgSrcSanitizationWhitelist(/^\s*(https?|ftp|mailto|file|tel):/);
        })

        .controller('DashCtrl', function ($scope, $cordovaDevice) {

          // RECUPERAR INFORMACOES DO DEVICE
          document.addEventListener("deviceready", function () {
            $scope.device = $cordovaDevice.getDevice(); // Recupera todo obj device.
            $scope.cordova = $cordovaDevice.getCordova();
            $scope.model = $cordovaDevice.getModel();
            $scope.platform = $cordovaDevice.getPlatform();
            $scope.uuid = $cordovaDevice.getUUID();
            $scope.version = $cordovaDevice.getVersion();
          }, false);

        })

        .controller('ChatsCtrl', function ($scope, Chats) {
          // With the new view caching in Ionic, Controllers are only called
          // when they are recreated or on app start, instead of every page change.
          // To listen for when this page is active (for example, to refresh data),
          // listen for the $ionicView.enter event:
          //
          //$scope.$on('$ionicView.enter', function(e) {
          //});

          $scope.chats = Chats.all();
          $scope.remove = function (chat) {
            Chats.remove(chat);
          };
        })

        .controller('ChatDetailCtrl', function ($scope, $stateParams, Chats) {
          $scope.chat = Chats.get($stateParams.chatId);
        })

        .controller('AccountCtrl', function ($scope, $ionicModal) {
          $ionicModal.fromTemplateUrl('modal.html', {
            scope: $scope,
            animation: 'slide-in-up'
          }).then(function (modal) {
            $scope.modal = modal;
          });

          $scope.openModal = function () {
            $scope.modal.show();
          };
          $scope.closeModal = function () {
            $scope.modal.hide();
          };
          //Cleanup the modal when we're done with it!
          $scope.$on('$destroy', function () {
            $scope.modal.remove();
          });
          // Execute action on hide modal
          $scope.$on('modal.hidden', function () {
            // Execute action
          });
          // Execute action on remove modal
          $scope.$on('modal.removed', function () {
            // Execute action
          });

          $scope.settings = {
            enableFriends: true
          };
          $scope.salvar = function (contato) {
            // IMPLEMENTAR AQUI A LOGICA PARA SALVAR LOCALMENTE, CHAMAR WS, ETC.
            $scope.title = "Sucesso";
            $scope.mensagem = "Contato " + contato.nome + " salvo com sucesso";
            $scope.openModal();
          };
        })

        .controller('CameraCtrl', function ($scope, $cordovaCamera, $cordovaVibration,
                $cordovaGeolocation) {

          $scope.latitude = 0;
          $scope.longitude = 0;

          $scope.vibrar = function () {
            $cordovaVibration.vibrate(1000);
          };

          $scope.tirarFoto = function () {

            // EXECUTA A FUNCAO APOS INICIALIZACAO DA CAMERA (DEVICEREADY)
            document.addEventListener("deviceready", function () {
              var options = {
                quality: 75,
                destinationType: Camera.DestinationType.FILE_URI,
                sourceType: Camera.PictureSourceType.CAMERA,
                /*allowEdit: true,*/
                encodingType: Camera.EncodingType.JPEG,
                targetWidth: 400,
                targetHeight: 400,
                /*popoverOptions: CameraPopoverOptions,*/
                saveToPhotoAlbum: true
              };

              // OBTEM IMAGEM DA CAMERA
              $cordovaCamera.getPicture(options).then(function (imageData) {
                //$scope.imageSrc = "data:image/jpeg;base64," + imageData;
                $scope.imageSrc = imageData;
              }, function (err) {
                // error
              });

            }, false);
          };

          $scope.selecionarFoto = function () {
            var options = {
              quality: 75,
              destinationType: Camera.DestinationType.FILE_URI,
              sourceType: Camera.PictureSourceType.PHOTOLIBRARY,
              /*allowEdit: true,*/
              encodingType: Camera.EncodingType.JPEG,
              targetWidth: 400,
              targetHeight: 400
                      /*popoverOptions: CameraPopoverOptions,*/
                      /*saveToPhotoAlbum: false*/
            };

            $cordovaCamera.getPicture(options).then(function (imageData) {
              //$scope.imageSrc = "data:image/jpeg;base64," + imageData;
              $scope.imageSrc = imageData;
            }, function (err) {
              // An error occured. Show a message to the user
            });

          };

          $scope.obterLocalizacao = function () {
            // OBTEM INFORMACOES DA LOCALIZACAO
            var posOptions = {timeout: 10000, enableHighAccuracy: true};
            $cordovaGeolocation
                    .getCurrentPosition(posOptions)
                    .then(function (position) {
                      $scope.latitude = position.coords.latitude;
                      $scope.longitude = position.coords.longitude;
                    }, function (err) {
                      // error
                    });
          };
        });