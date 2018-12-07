create table exchange_slips (
  slip_id uuid primary key default gen_random_uuid(),
  number varchar(127) not null,
  description text,
  sender_id uuid not null references shops,
  receiver_id uuid not null references shops,
  approved_at timestamp with time zone not null,
  published_at timestamp with time zone not null,
  unique (number, sender_id)
);
create table purchase_slips (
  slip_id uuid primary key default gen_random_uuid(),
  number varchar(127) not null,
  description text,
  sender_id uuid not null references publishers,
  receiver_id uuid not null references shops,
  approved_at timestamp with time zone not null,
  published_at timestamp with time zone not null,
  unique (number, sender_id)
);
create table sales_slips (
  slip_id uuid primary key default gen_random_uuid(),
  number varchar(127) not null,
  description text,
  sender_id uuid not null references shops,
  receiver_id uuid null references shops,
  approved_at timestamp with time zone not null,
  published_at timestamp with time zone not null,
  unique (number, sender_id)
);

comment on column sales_slips.receiver_id is 'receiver_id はあとから設定されることがあるので null をとる'
