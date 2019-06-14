/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MModelUniformBottomResourceDetailComponent } from 'app/entities/m-model-uniform-bottom-resource/m-model-uniform-bottom-resource-detail.component';
import { MModelUniformBottomResource } from 'app/shared/model/m-model-uniform-bottom-resource.model';

describe('Component Tests', () => {
  describe('MModelUniformBottomResource Management Detail Component', () => {
    let comp: MModelUniformBottomResourceDetailComponent;
    let fixture: ComponentFixture<MModelUniformBottomResourceDetailComponent>;
    const route = ({ data: of({ mModelUniformBottomResource: new MModelUniformBottomResource(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MModelUniformBottomResourceDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MModelUniformBottomResourceDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MModelUniformBottomResourceDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mModelUniformBottomResource).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
