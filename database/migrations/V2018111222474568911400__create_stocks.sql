create table stocks (
  plu_code text primary key,
  shop_id uuid not null references shops,
  product_id uuid not null references products,
  amount integer not null check (amount >= 0),
  price integer not null check (price >= 0)
)
