import { CursosService } from './../../../services/cursos.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { ICursos } from 'src/app/models/cursos';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-form-cursos',
  templateUrl: './form-cursos.component.html',
  styleUrls: ['./form-cursos.component.scss']
})
export class FormCursosComponent implements OnInit {

  formGroup: FormGroup;
  titleAlert: string = 'Este campo é obrigatório';
  isEdit: boolean = false;
  id: number = 0;

  constructor(
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private router: Router,
    private activeRouter: ActivatedRoute,
    private _snackBar: MatSnackBar,
    private cursosService: CursosService
    ) { }

  ngOnInit(): void {
    this.createForm();
    this.fillCursosForm();
  }

  public cursos: ICursos = {} as ICursos

  success = 'Salvo com sucesso!';
  action = 'fechar';

  createForm() {
    this.formGroup = this.formBuilder.group({
      nome: [null, Validators.required]
    });
  }

  get nome() {
    return this.formGroup.get('nome') as FormControl;
  }

  getErrorName() {
    return this.formGroup.get('name')?.hasError('required')
      ? 'Este campo é obrigatório'
      : this.formGroup.get('name')?.hasError('pattern')
      ? 'Não é um nome válido'
      : '';
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 3000
    });
  }

  public async saveCursos() {
    try {
      if (this.isEdit) {
        await this.cursosService.updateCursos(this.id, this.cursos);
        this.formGroup.reset();
        this.openSnackBar(this.success, this.action);
      } else {
        await this.cursosService.postCursos(this.cursos);
        this.formGroup.reset();
        this.openSnackBar(this.success, this.action);
      }
    } catch (e: any) {
      console.log('error');
      console.log(this.cursos);
      this.openSnackBar('Error', this.action);
    }
    setTimeout(() => {
      this.router.navigateByUrl('/main/cursos')
    });
  }

  private fillCursosForm() {
    if (this.activeRouter.snapshot.paramMap.get('id')) {
      this.isEdit = true;
      this.id = Number.parseInt(this.activeRouter.snapshot.paramMap.get('id')!);
      this.cursosService.findCursosById(this.id).subscribe({
        next: (res) => {
          this.formGroup.patchValue(res);
        },
        error: (ex) => {
          console.log(ex);
        },
      });
    }
  }

  delete() {
    if (confirm('Você está prestes a apagar esse registro, esta ação não pode ser desfeita!'))
    {
      this.cursosService.delete(this.id).subscribe({
        next: (n) => {
          this.openSnackBar('Registro apagado com sucesso', 'fechar');
          this.router.navigateByUrl('/main/cursos');
        },
        error: (e) => {
          alert('Cursos não pode ser apagado pois está vinculado a uma oferta!')
        }
      });
    } else {
      alert('cancelado!')
    }
  }

  voltar() {
    this.router.navigateByUrl('/main/cursos');
  }

}
