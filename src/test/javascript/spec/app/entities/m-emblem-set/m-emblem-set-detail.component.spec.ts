/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MEmblemSetDetailComponent } from 'app/entities/m-emblem-set/m-emblem-set-detail.component';
import { MEmblemSet } from 'app/shared/model/m-emblem-set.model';

describe('Component Tests', () => {
  describe('MEmblemSet Management Detail Component', () => {
    let comp: MEmblemSetDetailComponent;
    let fixture: ComponentFixture<MEmblemSetDetailComponent>;
    const route = ({ data: of({ mEmblemSet: new MEmblemSet(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MEmblemSetDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MEmblemSetDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MEmblemSetDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mEmblemSet).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
