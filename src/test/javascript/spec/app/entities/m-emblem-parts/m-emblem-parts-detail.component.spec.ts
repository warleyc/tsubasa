/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MEmblemPartsDetailComponent } from 'app/entities/m-emblem-parts/m-emblem-parts-detail.component';
import { MEmblemParts } from 'app/shared/model/m-emblem-parts.model';

describe('Component Tests', () => {
  describe('MEmblemParts Management Detail Component', () => {
    let comp: MEmblemPartsDetailComponent;
    let fixture: ComponentFixture<MEmblemPartsDetailComponent>;
    const route = ({ data: of({ mEmblemParts: new MEmblemParts(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MEmblemPartsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MEmblemPartsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MEmblemPartsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mEmblemParts).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
