/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MCardLevelDetailComponent } from 'app/entities/m-card-level/m-card-level-detail.component';
import { MCardLevel } from 'app/shared/model/m-card-level.model';

describe('Component Tests', () => {
  describe('MCardLevel Management Detail Component', () => {
    let comp: MCardLevelDetailComponent;
    let fixture: ComponentFixture<MCardLevelDetailComponent>;
    const route = ({ data: of({ mCardLevel: new MCardLevel(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCardLevelDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MCardLevelDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MCardLevelDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mCardLevel).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
