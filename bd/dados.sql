INSERT INTO public.fornecedor (cnpj, nome) VALUES
('12.345.678/0001-99', 'Fornecedor A'),
('98.765.432/0001-00', 'Fornecedor B'),
('56.789.123/0001-11', 'Fornecedor C');


INSERT INTO public.produto (codigo, descricao, estoque_minimo, lucro, preco_de_compra, preco_de_venda, quantidade, fornecedor_id) VALUES
(101, 'Produto 1', 5, 0.2, 10.00, 12.00, 100, (SELECT id FROM public.fornecedor WHERE nome = 'Fornecedor A' LIMIT 1)),
(102, 'Produto 2', 10, 0.15, 20.00, 23.00, 50, (SELECT id FROM public.fornecedor WHERE nome = 'Fornecedor B' LIMIT 1)),
(103, 'Produto 3', 8, 0.25, 5.00, 6.25, 150, (SELECT id FROM public.fornecedor WHERE nome = 'Fornecedor C' LIMIT 1));

INSERT INTO public.estoque (data_validade, quantidade, id_fornecedor, produto_id) VALUES
('2024-12-31', 100, 
 (SELECT id FROM public.fornecedor WHERE nome = 'Fornecedor A' LIMIT 1), 
 (SELECT id FROM public.produto WHERE descricao = 'Produto 1' LIMIT 1)),
('2024-06-30', 50, 
 (SELECT id FROM public.fornecedor WHERE nome = 'Fornecedor B' LIMIT 1), 
 (SELECT id FROM public.produto WHERE descricao = 'Produto 2' LIMIT 1)),
('2023-01-01', 20, 
 (SELECT id FROM public.fornecedor WHERE nome = 'Fornecedor C' LIMIT 1), 
 (SELECT id FROM public.produto WHERE descricao = 'Produto 3' LIMIT 1)); -- Este produto estÃ¡ vencido