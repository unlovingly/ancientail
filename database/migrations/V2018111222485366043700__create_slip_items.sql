-- 本当はひとつにしたいが、slip への外部キー参照をまとめられない
create table exchange_slip_items (
  slip_item_id uuid primary key default gen_random_uuid(),
  product_id uuid not null references products,
  amount integer not null check (amount > 0),
  price integer not null check (price > 0),
  slip_id uuid not null references exchange_slips
);
create table purchase_slip_items (
  slip_item_id uuid primary key default gen_random_uuid(),
  product_id uuid not null references products,
  amount integer not null check (amount > 0),
  price integer not null check (price > 0),
  slip_id uuid not null references purchase_slips
);
create table sales_slip_items (
  slip_item_id uuid primary key default gen_random_uuid(),
  plu_code text not null references stocks,
  amount integer not null check (amount > 0),
  price integer not null check (price > 0),
  slip_id uuid not null references sales_slips
);
