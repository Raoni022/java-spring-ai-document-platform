# Automação Jurídica com IA

Este projeto foca no desenvolvimento de sistemas jurídicos inteligentes para geração automática de petições e documentos, utilizando IA para otimizar processos e garantir precisão técnica.

## 🛠️ Tecnologias
- Python (FastAPI)
- GPT-4 (OpenAI)
- Supabase (Storage + Vetores)
- LangChain
- DocxTemplater
- n8n
- Lovable (Front-end)

## 🚀 Funcionalidades
- Geração de petições com base em modelos `.docx` estruturados.
- Substituição inteligente de variáveis, mantendo formatação e tipografia jurídica.
- Reescrita de trechos como "FATOS" e "FUNDAMENTAÇÃO" com apoio da IA.
- Vetorização de modelos jurídicos para busca semântica.
- Validação de documentos com IA antes da entrega ao cliente.
- Fluxos integrados com front-end e automações.

## 🧪 Como usar
```bash
git clone https://github.com/Raoni022/Automacao_Juridica_IA.git
cd Automacao_Juridica_IA
pip install -r requirements.txt
uvicorn src.main:app --reload
```

## 📄 Estrutura do Projeto
```
Automacao_Juridica_IA/
├── README.md
├── .gitignore
├── requirements.txt
├── src/
│   ├── main.py
│   └── utils.py
├── docs/
│   ├── fluxo-n8n.json
│   ├── modelo-exemplo.docx
│   └── imagens/
├── examples/
│   ├── input.json
│   └── output.docx
└── .env.example
```


