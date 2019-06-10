/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MDummyEmblemDetailComponent } from 'app/entities/m-dummy-emblem/m-dummy-emblem-detail.component';
import { MDummyEmblem } from 'app/shared/model/m-dummy-emblem.model';

describe('Component Tests', () => {
  describe('MDummyEmblem Management Detail Component', () => {
    let comp: MDummyEmblemDetailComponent;
    let fixture: ComponentFixture<MDummyEmblemDetailComponent>;
    const route = ({ data: of({ mDummyEmblem: new MDummyEmblem(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDummyEmblemDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MDummyEmblemDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MDummyEmblemDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mDummyEmblem).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
