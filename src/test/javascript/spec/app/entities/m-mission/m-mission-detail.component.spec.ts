/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MMissionDetailComponent } from 'app/entities/m-mission/m-mission-detail.component';
import { MMission } from 'app/shared/model/m-mission.model';

describe('Component Tests', () => {
  describe('MMission Management Detail Component', () => {
    let comp: MMissionDetailComponent;
    let fixture: ComponentFixture<MMissionDetailComponent>;
    const route = ({ data: of({ mMission: new MMission(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMissionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MMissionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MMissionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mMission).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
