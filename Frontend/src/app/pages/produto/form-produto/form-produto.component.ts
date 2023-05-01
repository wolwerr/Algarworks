import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { IProduto } from 'src/app/models/produto';
import { ProdutoService } from 'src/app/services/produto.service';


@Component({
  selector: 'app-form-produto',
  templateUrl: './form-produto.component.html',
  styleUrls: ['./form-produto.component.scss'],
})
export class FormProdutoComponent implements OnInit {
  formGroup: FormGroup;
  titleAlert: string = 'Este campo é obrigatório';
  isEdit: boolean = false;
  id: number;

  constructor(
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private produtoService: ProdutoService,
    private _snackBar: MatSnackBar,
    private activeRouter: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.createForm();
    this.fillProdutoForm();
  }

  public produto: IProduto = {} as IProduto;


  success = 'Salvo com sucesso!';
  action = 'fechar';

  createForm() {

    this.formGroup = this.formBuilder.group({
      name: [null, [Validators.required]],
      status: [null, [Validators.required]],
    });
  }

  get name() {
    return this.formGroup.get('name') as FormControl;
  }

  get status() {
    return this.formGroup.get('status') as FormControl;
  }


  getErrorName() {
    return this.formGroup.get('name')?.hasError('required')
      ? 'Este campo é obrigatório'
  : '';
  }

  getErrorStatus() {
    return this.formGroup.get('status')?.hasError('required')
      ? 'Este campo é obrigatório'
      : '';
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 3000,
    });
  }

  public async saveProduto() {
    try {
      if (this.isEdit) {
        await this.produtoService.updateProduto(this.id, this.produto);
        this.formGroup.reset();
        this.openSnackBar(this.success, this.action);
      } else {
        await this.produtoService.postProduto(this.produto);
        this.formGroup.reset();
        this.openSnackBar(this.success, this.action);
      }
    } catch (e: any) {
      console.log('error');
      console.log(this.produto);
      this.openSnackBar('Error', this.action);
    }
    setTimeout(() => {
      this.router.navigate(['/main/produto'])
    });
  }

  private fillProdutoForm() {
    if (this.activeRouter.snapshot.paramMap.get('id')) {
      this.isEdit = true;
      this.id = Number.parseInt(this.activeRouter.snapshot.paramMap.get('id')!);
      this.produtoService.findProdutoById(this.id).subscribe({
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
      this.produtoService.delete(this.id).subscribe({
        next: (n) => {
          this.openSnackBar('Registro apagado com sucesso', 'fechar');
          this.router.navigateByUrl('/main/produto');
        },
        error: (e) => {
          alert('Produto não pode ser apagado pois está vinculado a uma oferta!')
          this.router.navigateByUrl('/main/produto');
        }
      });
    } else {
      alert('cancelado!')
    }
  }

  voltar() {
    this.router.navigateByUrl('/main/produto');
  }

}
