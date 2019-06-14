/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGuildLevelDetailComponent } from 'app/entities/m-guild-level/m-guild-level-detail.component';
import { MGuildLevel } from 'app/shared/model/m-guild-level.model';

describe('Component Tests', () => {
  describe('MGuildLevel Management Detail Component', () => {
    let comp: MGuildLevelDetailComponent;
    let fixture: ComponentFixture<MGuildLevelDetailComponent>;
    const route = ({ data: of({ mGuildLevel: new MGuildLevel(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGuildLevelDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MGuildLevelDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGuildLevelDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mGuildLevel).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
