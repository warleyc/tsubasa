/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MArousalMaterialDetailComponent } from 'app/entities/m-arousal-material/m-arousal-material-detail.component';
import { MArousalMaterial } from 'app/shared/model/m-arousal-material.model';

describe('Component Tests', () => {
  describe('MArousalMaterial Management Detail Component', () => {
    let comp: MArousalMaterialDetailComponent;
    let fixture: ComponentFixture<MArousalMaterialDetailComponent>;
    const route = ({ data: of({ mArousalMaterial: new MArousalMaterial(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MArousalMaterialDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MArousalMaterialDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MArousalMaterialDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mArousalMaterial).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
