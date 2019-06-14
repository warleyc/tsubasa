/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTsubasaPointDetailComponent } from 'app/entities/m-tsubasa-point/m-tsubasa-point-detail.component';
import { MTsubasaPoint } from 'app/shared/model/m-tsubasa-point.model';

describe('Component Tests', () => {
  describe('MTsubasaPoint Management Detail Component', () => {
    let comp: MTsubasaPointDetailComponent;
    let fixture: ComponentFixture<MTsubasaPointDetailComponent>;
    const route = ({ data: of({ mTsubasaPoint: new MTsubasaPoint(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTsubasaPointDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MTsubasaPointDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTsubasaPointDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mTsubasaPoint).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
