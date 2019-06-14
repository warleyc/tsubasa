/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MModelUniformUpDetailComponent } from 'app/entities/m-model-uniform-up/m-model-uniform-up-detail.component';
import { MModelUniformUp } from 'app/shared/model/m-model-uniform-up.model';

describe('Component Tests', () => {
  describe('MModelUniformUp Management Detail Component', () => {
    let comp: MModelUniformUpDetailComponent;
    let fixture: ComponentFixture<MModelUniformUpDetailComponent>;
    const route = ({ data: of({ mModelUniformUp: new MModelUniformUp(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MModelUniformUpDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MModelUniformUpDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MModelUniformUpDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mModelUniformUp).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
