/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MModelUniformBottomDetailComponent } from 'app/entities/m-model-uniform-bottom/m-model-uniform-bottom-detail.component';
import { MModelUniformBottom } from 'app/shared/model/m-model-uniform-bottom.model';

describe('Component Tests', () => {
  describe('MModelUniformBottom Management Detail Component', () => {
    let comp: MModelUniformBottomDetailComponent;
    let fixture: ComponentFixture<MModelUniformBottomDetailComponent>;
    const route = ({ data: of({ mModelUniformBottom: new MModelUniformBottom(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MModelUniformBottomDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MModelUniformBottomDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MModelUniformBottomDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mModelUniformBottom).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
