/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MPvpWaveDetailComponent } from 'app/entities/m-pvp-wave/m-pvp-wave-detail.component';
import { MPvpWave } from 'app/shared/model/m-pvp-wave.model';

describe('Component Tests', () => {
  describe('MPvpWave Management Detail Component', () => {
    let comp: MPvpWaveDetailComponent;
    let fixture: ComponentFixture<MPvpWaveDetailComponent>;
    const route = ({ data: of({ mPvpWave: new MPvpWave(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MPvpWaveDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MPvpWaveDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MPvpWaveDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mPvpWave).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
