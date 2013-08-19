ALTER TABLE `posts` 
  ADD COLUMN `created_at` TIMESTAMP NOT NULL DEFAULT current_timestamp, 
  ADD COLUMN `updated_at` TIMESTAMP NULL  AFTER `created_at` ;