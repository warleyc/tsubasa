/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MModelUniformUpResourceDetailComponent } from 'app/entities/m-model-uniform-up-resource/m-model-uniform-up-resource-detail.component';
import { MModelUniformUpResource } from 'app/shared/model/m-model-uniform-up-resource.model';

describe('Component Tests', () => {
  describe('MModelUniformUpResource Management Detail Component', () => {
    let comp: MModelUniformUpResourceDetailComponent;
    let fixture: ComponentFixture<MModelUniformUpResourceDetailComponent>;
    const route = ({ data: of({ mModelUniformUpResource: new MModelUniformUpResource(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MModelUniformUpResourceDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MModelUniformUpResourceDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MModelUniformUpResourceDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mModelUniformUpResource).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
