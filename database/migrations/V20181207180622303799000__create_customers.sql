create table customers (
  customer_id uuid primary key default gen_random_uuid(),
  name text not null check (name <> '')
)
