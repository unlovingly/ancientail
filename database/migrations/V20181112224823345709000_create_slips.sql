create table exchange_slips (
  slip_id uuid primary key default gen_random_uuid(),
  sender_id uuid not null references publishers,
  shop_id uuid not null references shops,
  approved_at timestamp with time zone not null,
  published_at timestamp with time zone not null
)
create table purchase_slips (
  slip_id uuid primary key default gen_random_uuid(),
  sender_id uuid not null references shops, ; 全伝票をまとめるビューを作るかもしれない
  shop_id uuid not null references shops,
  approved_at timestamp with time zone not null,
  published_at timestamp with time zone not null
)
create table sales_slips (
  slip_id uuid primary key default gen_random_uuid(),
  sender_id uuid not null references shops,
  shop_id uuid not null references shops,
  approved_at timestamp with time zone not null,
  published_at timestamp with time zone not null
)