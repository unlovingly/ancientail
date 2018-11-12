create table slip_items (
  slip_item_id uuid primary key default gen_random_uuid(),
  product_id uuid not null references products,
  amount integer not null check (amount > 0),
  price integer not null check (price > 0),
  slip_id uuid not null references slips
)
