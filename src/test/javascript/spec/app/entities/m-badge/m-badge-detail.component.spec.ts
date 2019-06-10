/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MBadgeDetailComponent } from 'app/entities/m-badge/m-badge-detail.component';
import { MBadge } from 'app/shared/model/m-badge.model';

describe('Component Tests', () => {
  describe('MBadge Management Detail Component', () => {
    let comp: MBadgeDetailComponent;
    let fixture: ComponentFixture<MBadgeDetailComponent>;
    const route = ({ data: of({ mBadge: new MBadge(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MBadgeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MBadgeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MBadgeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mBadge).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
