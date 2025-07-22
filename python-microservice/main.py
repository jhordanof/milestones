from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from fastapi.responses import JSONResponse
import time

app = FastAPI(title="Python Microservice", version="1.0.0")


class ConvertRequest(BaseModel):
    text: str
    to_upper: bool


class ConvertResponse(BaseModel):
    original: str
    convertido: str


@app.post("/convert", response_model=ConvertResponse, summary="Convertir texto a mayusculas o minusculas")
def convert(req: ConvertRequest):
    if not req.text.strip():
        raise HTTPException(status_code=400, detail="El texto no puede estar vacio")

    converted = req.text.upper() if req.to_upper else req.text.lower()
    return ConvertResponse(original=req.text, convertido=converted)

@app.get("/ping", summary="Verificar conexion y tiempo de respuesta")
def ping():
    start = time.time()
    duration = round((time.time() - start) * 1000, 2)  # milisegundos
    return {"response_time": duration}