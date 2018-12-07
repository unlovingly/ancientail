# ルート パッケージ
## ambientendre
製品ドメイン

## ancientail
在庫管理ドメイン

## blessedict
顧客管理ドメイン
  
## パッケージ類
### outsiders
インフラ層

#### `play.ApplicationLoader`, `play.components`
今は愚直にコンストラクター インジェクションだけど、そのうち Airframe 入れてもいいかもね

### usecases
ドメイン サービス層

# 簡単用語集
## 製品
製品情報

## 商品
店舗が持つ製品。在庫数、価格をあわせてもつ

## (入荷 - receiving、入庫 - storing)
本来は別、このアプリケーションでは入庫のみ

## (出荷 - issuing、出庫 - shipping)
同上
