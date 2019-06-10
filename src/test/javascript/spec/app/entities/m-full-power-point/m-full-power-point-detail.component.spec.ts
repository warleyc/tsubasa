/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MFullPowerPointDetailComponent } from 'app/entities/m-full-power-point/m-full-power-point-detail.component';
import { MFullPowerPoint } from 'app/shared/model/m-full-power-point.model';

describe('Component Tests', () => {
  describe('MFullPowerPoint Management Detail Component', () => {
    let comp: MFullPowerPointDetailComponent;
    let fixture: ComponentFixture<MFullPowerPointDetailComponent>;
    const route = ({ data: of({ mFullPowerPoint: new MFullPowerPoint(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MFullPowerPointDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MFullPowerPointDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MFullPowerPointDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mFullPowerPoint).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
