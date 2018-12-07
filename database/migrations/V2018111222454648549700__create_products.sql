create table products (
  product_id uuid primary key default gen_random_uuid(),
  name text not null check (name <> ''),
  publisher_id uuid not null references publishers
)
