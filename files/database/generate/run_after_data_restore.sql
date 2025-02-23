SELECT setval('message_message_id_seq', (SELECT MAX(message_id) FROM message));
SELECT setval('conversation_participant_id_seq', (SELECT MAX(id) FROM conversation_participant));
SELECT setval('conversation_conversation_id_seq', (SELECT MAX(conversation_id) FROM conversation));
SELECT setval('product_specification_specification_id_seq', (SELECT MAX(specification_id) FROM product_specification));
SELECT setval('product_product_id_seq', (SELECT MAX(product_id) FROM product));
SELECT setval('users_user_id_seq', (SELECT MAX(user_id) FROM users));
SELECT setval('product_category_product_category_id_seq', (SELECT MAX(product_category_id) FROM product_category));
SELECT setval('product_like_id_seq', (SELECT MAX(id) FROM product_like));
SELECT setval('product_image_id_seq', (SELECT MAX(id) FROM product_image));
SELECT setval('security_code_id_seq', (SELECT MAX(id) FROM security_code));
SELECT setval('action_log_id_seq', (SELECT MAX(id) FROM action_log));





