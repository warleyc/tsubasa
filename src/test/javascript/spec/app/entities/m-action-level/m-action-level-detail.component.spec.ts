/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MActionLevelDetailComponent } from 'app/entities/m-action-level/m-action-level-detail.component';
import { MActionLevel } from 'app/shared/model/m-action-level.model';

describe('Component Tests', () => {
  describe('MActionLevel Management Detail Component', () => {
    let comp: MActionLevelDetailComponent;
    let fixture: ComponentFixture<MActionLevelDetailComponent>;
    const route = ({ data: of({ mActionLevel: new MActionLevel(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MActionLevelDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MActionLevelDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MActionLevelDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mActionLevel).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
