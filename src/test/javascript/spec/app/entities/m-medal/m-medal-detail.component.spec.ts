/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MMedalDetailComponent } from 'app/entities/m-medal/m-medal-detail.component';
import { MMedal } from 'app/shared/model/m-medal.model';

describe('Component Tests', () => {
  describe('MMedal Management Detail Component', () => {
    let comp: MMedalDetailComponent;
    let fixture: ComponentFixture<MMedalDetailComponent>;
    const route = ({ data: of({ mMedal: new MMedal(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMedalDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MMedalDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MMedalDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mMedal).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
