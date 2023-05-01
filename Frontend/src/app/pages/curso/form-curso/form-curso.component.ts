import { async } from '@angular/core/testing';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { ICurso } from 'src/app/models/curso';
import { CursoService } from 'src/app/services/curso.service';

@Component({
  selector: 'app-form-curso',
  templateUrl: './form-curso.component.html',
  styleUrls: ['./form-curso.component.scss'],
})

export class FormCursoComponent implements OnInit {

  formGroup: FormGroup;
  titleAlert: string = 'Este campo é obrigatório';
  isEdit: boolean = false;
  id: number;

  constructor(private formBuilder: FormBuilder,
    private http: HttpClient,
    private cursoService: CursoService,
    private _snackBar: MatSnackBar,
    private activeRouter: ActivatedRoute,
    private router: Router) {}

  ngOnInit(): void {
    this.createForm();
    this.fillCursoForm();
  }

  public curso: ICurso = {} as ICurso;

  success = 'Salvo com sucesso!';
  action = 'fechar';



  createForm() {

    this.formGroup = this.formBuilder.group({
      name: [null, [Validators.required],
    ]
    });
  }

  get name() {
    return this.formGroup.get('name') as FormControl;
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

  public async saveCurso() {
    try {
      if (this.isEdit) {
        await this.cursoService.updateCurso(this.id, this.curso);
        this.formGroup.reset();
        this.openSnackBar(this.success, this.action);
      } else {
        await this.cursoService.postCurso(this.curso);
        this.formGroup.reset();
        this.openSnackBar(this.success, this.action);
      }
    } catch (e: any) {
      console.log('error');
      console.log(this.curso);
      this.openSnackBar('Error', this.action);
    }
    setTimeout(() => {
      this.router.navigateByUrl('/main/curso')
    });
  }

  private fillCursoForm() {
    if (this.activeRouter.snapshot.paramMap.get('id')) {
      this.isEdit = true;
      this.id = Number.parseInt(this.activeRouter.snapshot.paramMap.get('id')!);
      this.cursoService.findCursoById(this.id).subscribe({
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
      this.cursoService.delete(this.id).subscribe({
        next: (n) => {
          this.openSnackBar('Registro apagado com sucesso', 'fechar');
          this.router.navigateByUrl('/main/curso');
        },
        error: (e) => {
          alert('Curso não pode ser apagado pois está vinculado a uma oferta!')
          this.router.navigateByUrl('/main/curso');
        }
      });
    } else {
      alert('cancelado!')
    }
  }

  voltar() {
    this.router.navigateByUrl('/main/curso');
  }

}
